package ru.pobopo.weather.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pobopo.weather.exception.ValidationException;
import ru.pobopo.weather.model.WeatherObject;
import ru.pobopo.weather.service.WeatherService;

@RestController
@RequestMapping("/v1")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/forecast")
    public WeatherObject getForecast(@RequestParam String city, @RequestParam String dt)
        throws ValidationException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return weatherService.getForecast(city, LocalDateTime.parse(dt, formatter));
    }

    @GetMapping("/current")
    public WeatherObject getCurrent(@RequestParam String city) throws ValidationException, IOException {
        return weatherService.getCurrent(city);
    }

    @PutMapping("/forecast")
    public void saveWeather(@RequestBody WeatherObject weatherObject) throws ValidationException {
        weatherService.saveWeather(weatherObject);
    }
}
