package ru.pobopo.weather.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
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
import ru.pobopo.weather.exception.ValidationException;
import ru.pobopo.weather.model.WeatherResponse;
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

    public WeatherService() {
        baseUrl = System.getenv("BASE_URL");
        apiKey = System.getenv("API_KEY");
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

    public WeatherResponse getForecast(String city, LocalDateTime dateTime) throws IOException, ValidationException {
        if (StringUtils.isBlank(city)) {
            throw new ValidationException("City can't be blank!");
        }
        Objects.requireNonNull(dateTime, "Date is missing!");
        // bruh
        long days = LocalDateTime.now().until(dateTime, ChronoUnit.DAYS) + 2;
        log.info("Searching forecast for days: " + days);

        Map<String, String> params = new HashMap<>();
        addDefaultParameters(params);
        params.put("q", city);
        params.put("days", String.valueOf(days));

        WeatherApiResponse apiResponse = makeRequest("/forecast.json", params);

        //bruh x2
        ForecastDay forecastDay = apiResponse.getForecast().getForecastday().get(apiResponse.getForecast().getForecastday().size() - 1);
        TempHour tempHour = forecastDay.getHour().get(dateTime.getHour());

        return new WeatherResponse(
            apiResponse.getLocation().getName(),
            tempHour.getTemp_c(),
            "celsius"
        );
    }

    public WeatherResponse getCurrent(String city) throws IOException, ValidationException {
        if (StringUtils.isBlank(city)) {
            throw new ValidationException("City can't be blank!");
        }
        Map<String, String> params = new HashMap<>();
        addDefaultParameters(params);
        params.put("q", city);

        WeatherApiResponse apiResponse = makeRequest("/current.json", params);

        return new WeatherResponse(
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

        log.info("Weather api response: " + content.toString());

        if (status > 299) {
            throw new RuntimeException(content.toString());
        }
        return gson.fromJson(content.toString(), WeatherApiResponse.class);
    }

    private void addDefaultParameters(Map<String, String> params) {
        params.put("key", apiKey);
        params.put("aqi", "no");
    }

}
