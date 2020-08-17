package pl.travel360.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ActualWeather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long temperature;
    private Long cloud;
    private Long wind;
    private String windDirection;
    private LocalDateTime date;
    @OneToOne
    private City city;
}
