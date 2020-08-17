package pl.travel360.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.travel360.config.WeatherApiConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeatherClient {
    private final RestTemplate restTemplate;
    private final WeatherApiConfig config;

    public GetActualWeatherApiResponse getActualWeatherForCity(String city) {
        String url = config.getApiUrl() + config.getApiGetCurrentWeatherPath();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("key", config.getApiKey());
        parameters.put("q",city);
        return restTemplate.getForObject(url, GetActualWeatherApiResponse.class, parameters);
    }

    public List<DayWeatherResponse> getDailyWeatherForCity(String city) {
        String url = config.getApiUrl() + config.getApiGetDailyWeatherPath();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("key", config.getApiKey());
        parameters.put("q",city);
        parameters.put("d", 3);
        GetDailyWeatherAdiResponse response = restTemplate.getForObject(url, GetDailyWeatherAdiResponse.class, parameters);

        return response.getForecast().getForecastday();
    }
}
