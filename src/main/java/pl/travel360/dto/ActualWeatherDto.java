package pl.travel360.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import pl.travel360.model.City;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor(staticName = "of")
@Builder
@Setter
@Getter
@NoArgsConstructor
public class ActualWeatherDto {
    private Long id;
    private Long temperature;
    private Long cloud;
    private Long wind;
    private String windDirection;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy mm:ss")
    private LocalDateTime date;
    private City city;
}
