package ru.pobopo.model.weatherapi;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForecastDay {
    private LocalDate date;
    private TempDay day;
    private List<TempHour> hour;
}

@Data
class TempDay {
    private float maxtemp_c;
}
