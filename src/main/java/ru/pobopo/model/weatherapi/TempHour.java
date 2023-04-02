package ru.pobopo.model.weatherapi;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public
class TempHour {
    private LocalDateTime time;
    private float temp_c;
}
