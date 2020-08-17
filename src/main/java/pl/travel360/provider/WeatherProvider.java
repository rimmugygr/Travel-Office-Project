package pl.travel360.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.travel360.client.DayWeatherResponse;
import pl.travel360.client.GetActualWeatherApiResponse;
import pl.travel360.client.WeatherClient;
import pl.travel360.dto.ActualWeatherDto;
import pl.travel360.dto.DayWeatherDto;
import pl.travel360.mapper.ActualWeatherMapper;
import pl.travel360.mapper.DayWeatherMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherProvider {
    private final WeatherClient weatherClient;
    private final ActualWeatherMapper actualWeatherMapper;
    private final DayWeatherMapper dayWeatherMapper;

    public ActualWeatherDto getActualWeatherByCity(String city) {
        GetActualWeatherApiResponse actualWeatherResponse = weatherClient.getActualWeatherForCity(city);
        return actualWeatherMapper.map(actualWeatherResponse);
    }

    public List<DayWeatherDto> getDayWeatherByCity(String city) {
        List<DayWeatherResponse> dailyWeatherForCity = weatherClient.getDailyWeatherForCity(city);

        return dailyWeatherForCity.stream()
                .map(dayWeatherMapper::map)
                .collect(Collectors.toList());
    }
}
