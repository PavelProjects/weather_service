package ru.pobopo.weather.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherObject {
    private String city;
    private float temperature;
    private String unit;
    private LocalDateTime date;

    public WeatherObject(String city, float temperature, String unit) {
        this.city = city;
        this.temperature = temperature;
        this.unit = unit;
    }
}

