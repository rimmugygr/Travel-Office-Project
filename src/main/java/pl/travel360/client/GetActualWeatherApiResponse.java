package pl.travel360.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class GetActualWeatherApiResponse {
    @JsonProperty("current")
    private Current current;

    @Data
    public static class Current {
        @JsonProperty("temp_c")
        private Long temperature;
        @JsonProperty("cloud")
        private int cloud;
        @JsonProperty("wind_kph")
        private double wind;
        @JsonProperty("wind_dir")
        private String windDirection;
    }
}
