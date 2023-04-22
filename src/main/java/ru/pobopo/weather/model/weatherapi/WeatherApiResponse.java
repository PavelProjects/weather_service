package ru.pobopo.weather.model.weatherapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherApiResponse {
    private Location location;
    private CurrentWeather current;
    private Forecast forecast;
}

