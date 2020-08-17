package pl.travel360.dto;

import lombok.*;
import pl.travel360.model.City;
import pl.travel360.model.WeatherStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "of")
@Builder
@Setter
@Getter
@NoArgsConstructor
public class DayWeatherDto {
    private Long id;
    private Long temperature;
    private Long wind;
    private LocalDate date;
    private Long precipitation;
    private Long humidity;
    private String description;
    @Enumerated(EnumType.STRING)
    private WeatherStatus weatherStatus;
    private City city;
}
