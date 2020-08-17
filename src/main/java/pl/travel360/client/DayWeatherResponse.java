package pl.travel360.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Data
public class DayWeatherResponse {
    @JsonProperty("date")
    private LocalDate date;
    @JsonProperty("day")
    private Day day;

    @NoArgsConstructor
    @Getter
    @Setter
    @Data
    public static class Day {
        @JsonProperty("avgtemp_c")
        private Long temperature;
        @JsonProperty("maxwind_mph")
        private double wind;
        @JsonProperty("totalprecip_mm")
        private double precipitation;
        @JsonProperty( "avghumidity")
        private double humidity;
        @JsonProperty( "condition")
        private Condition description;

        @NoArgsConstructor
        @Getter
        @Setter
        @Data
        public static class Condition {
            @JsonProperty( "text")
            private String text;

        }

    }

}
