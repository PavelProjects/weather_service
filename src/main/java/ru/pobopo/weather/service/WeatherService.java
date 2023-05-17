package ru.pobopo.weather.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import ru.pobopo.weather.exception.ValidationException;
import ru.pobopo.weather.model.WeatherObject;
import ru.pobopo.weather.model.weatherapi.ForecastDay;
import ru.pobopo.weather.model.weatherapi.TempHour;
import ru.pobopo.weather.model.weatherapi.WeatherApiResponse;
import ru.pobopo.weather.util.ParameterStringBuilder;

@Slf4j
@Service
public class WeatherService {
    private final String baseUrl;
    private final String apiKey;
    private final Gson gson;
    private final JedisPool jedisPool;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd:hh");

    public WeatherService() {
        baseUrl = System.getenv("BASE_URL");
        apiKey = System.getenv("API_KEY");

        String redisHost = System.getenv("REDIS_HOST");
        String redisPort = System.getenv("REDIS_PORT");

        if (StringUtils.isBlank(redisHost)) {
            redisHost = "localhost";
        }
        if (StringUtils.isBlank(redisPort)) {
            redisPort = "6379";
        }
        log.info(String.format("Redis host/port %s:%s", redisHost, redisPort));
        jedisPool = new JedisPool(buildPoolConfig(), redisHost, Integer.parseInt(redisPort));
//        try (Jedis jedis = jedisPool.getResource()) {
//            log.warn("Flushing redis");
//            jedis.flushAll();
//        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class,
            (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    return LocalDateTime.parse(json.getAsString(), formatter);
                } catch (Exception ex) {
                    return LocalDateTime.now();
                }
            });
        gsonBuilder.registerTypeAdapter(LocalDate.class,
            (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    return LocalDate.parse(json.getAsString(), formatter);
                } catch (Exception ex) {
                    return LocalDate.now();
                }
            });
        gson = gsonBuilder.create();
    }

    public void saveWeather(WeatherObject weatherObject) throws ValidationException {
        if (weatherObject == null) {
            throw new ValidationException("Weather object missing!");
        }
        if (StringUtils.isBlank(weatherObject.getCity())) {
            throw new ValidationException("City is missing!");
        }
        if (weatherObject.getDate() == null) {
            throw new ValidationException("Date is missing!");
        }

        String body = gson.toJson(weatherObject);
        String key = buildKey(weatherObject.getCity(), weatherObject.getDate());

        log.info("Connecting to redis");
        try (Jedis jedis = jedisPool.getResource()) {
            log.info(String.format("Saving forecast to redis by key %s: %s", key, body));
            jedis.set(key, body);
            log.info("Forecast saved");
        }
    }

    public WeatherObject getForecast(String city, LocalDateTime dateTime) throws IOException, ValidationException {
        if (StringUtils.isBlank(city)) {
            throw new ValidationException("City can't be blank!");
        }
        Objects.requireNonNull(dateTime, "Date is missing!");
        WeatherObject weatherObject = getForecastFromRedis(city, dateTime);
        if (weatherObject != null) {
            return weatherObject;
        }

        long days = LocalDateTime.now().until(dateTime, ChronoUnit.DAYS) + 2;
        log.info("Searching forecast for days: " + days);

        Map<String, String> params = new HashMap<>();
        addDefaultParameters(params);
        params.put("q", city);
        params.put("days", String.valueOf(days));

        WeatherApiResponse apiResponse = makeRequest("/forecast.json", params);

        ForecastDay forecastDay = apiResponse.getForecast().getForecastday().get(apiResponse.getForecast().getForecastday().size() - 1);
        TempHour tempHour = forecastDay.getHour().get(dateTime.getHour());

        weatherObject = new WeatherObject(
            city,
            tempHour.getTemp_c(),
            "celsius",
            dateTime
        );
        saveWeather(weatherObject);
        return weatherObject;
    }

    private WeatherObject getForecastFromRedis(String city, LocalDateTime date) {
        String key = buildKey(city, date);
        log.info("Connecting to redis");
        try (Jedis jedis = jedisPool.getResource()) {
            log.info("Trying to get forecast from redis by key " + key);
            String body = jedis.get(key);
            if (StringUtils.isBlank(body)) {
                log.info("No forecast were found in redis by key " + key);
                return null;
            }
            log.info("Got forecast from redis " + body);
            return gson.fromJson(body, WeatherObject.class);
        }
    }

    public WeatherObject getCurrent(String city) throws IOException, ValidationException {
        if (StringUtils.isBlank(city)) {
            throw new ValidationException("City can't be blank!");
        }
        Map<String, String> params = new HashMap<>();
        addDefaultParameters(params);
        params.put("q", city);

        WeatherApiResponse apiResponse = makeRequest("/current.json", params);

        return new WeatherObject(
            apiResponse.getLocation().getName(),
            apiResponse.getCurrent().getTemp_c(),
            "celsius"
        );
    }

    private WeatherApiResponse makeRequest(String path, Map<String, String> params) throws IOException {
        URL url = new URL(baseUrl + path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);

        try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
            out.writeBytes(ParameterStringBuilder.getParamsString(params));
            out.flush();
        }

        int status = con.getResponseCode();
        Reader streamReader = null;

        if (status > 299) {
            streamReader = new InputStreamReader(con.getErrorStream());
        } else {
            streamReader = new InputStreamReader(con.getInputStream());
        }

        StringBuffer content = new StringBuffer();
        try (BufferedReader in = new BufferedReader(streamReader)) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        }
        con.disconnect();

        log.info("Got weather api response");

        if (status > 299) {
            throw new RuntimeException(content.toString());
        }
        return gson.fromJson(content.toString(), WeatherApiResponse.class);
    }

    private void addDefaultParameters(Map<String, String> params) {
        params.put("key", apiKey);
        params.put("aqi", "no");
    }

    private String buildKey(String city, LocalDateTime date) {
        return city + Objects.requireNonNull(date, "Date is missing!").format(formatter);
    }

    private JedisPoolConfig buildPoolConfig() {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }
}
