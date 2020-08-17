package pl.travel360.controller.response.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@Builder
public class GetOfferResponse {
    private Long id;
    private String cityName;
    private String countryName;
    private Long price;
    private String name;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
    private Long day;
    private WeatherResponse weather;


    @NoArgsConstructor
    @Getter
    @Setter
    @Data
    @AllArgsConstructor(staticName = "of")
    @Builder
    public static class WeatherResponse {
        private Long temperature;
        private int cloud;
        private double wind;
        private String windDirection;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy mm:ss")
        private LocalDateTime date;
    }
}
