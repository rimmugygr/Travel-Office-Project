package pl.travel360.config;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("application.weather")
@Data
@Setter
@Getter
@NoArgsConstructor
public class WeatherApiConfig {
    private  String apiUrl;
    private  String apiGetCurrentWeatherPath;
    private  String apiGetDailyWeatherPath;
    private  String apiKey;
}
