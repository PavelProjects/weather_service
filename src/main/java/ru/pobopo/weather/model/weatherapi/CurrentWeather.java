package ru.pobopo.weather.model.weatherapi;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentWeather implements Serializable {
    private float temp_c;
    private float temp_f;
}
