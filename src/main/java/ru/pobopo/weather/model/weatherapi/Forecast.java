package ru.pobopo.weather.model.weatherapi;

import java.util.List;
import lombok.Data;

@Data
public class Forecast {
    private List<ForecastDay> forecastday;
}
