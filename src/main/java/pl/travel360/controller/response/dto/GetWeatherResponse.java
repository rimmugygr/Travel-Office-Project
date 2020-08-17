package pl.travel360.controller.response.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class GetWeatherResponse {
    private String city;
    private Long temperature;
    private int cloud;
    private double wind;
    private String windDirection;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy mm:ss")
    private LocalDateTime date;
}
