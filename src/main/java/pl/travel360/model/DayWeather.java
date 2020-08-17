package pl.travel360.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Builder(toBuilder=true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DayWeather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long temperature;
    private Long wind;
    private LocalDate date;
    private Long precipitation;
    private Long humidity;
    private String description;
    @Enumerated(EnumType.STRING)
    private WeatherStatus weatherStatus;
    @ManyToOne
    private City city;
}
