package pl.travel360.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class GetDailyWeatherAdiResponse {
    @JsonProperty("forecast")
    private Forecast forecast;

    @NoArgsConstructor
    @Getter
    @Setter
    @Data
    @ToString
    @AllArgsConstructor
    @Builder
    public static class Forecast {
        @JsonProperty("forecastday")
        private List<DayWeatherResponse> forecastday = new ArrayList<>();

    }

}



