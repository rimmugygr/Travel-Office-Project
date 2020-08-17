package pl.travel360.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.travel360.controller.response.dto.GetWeatherResponse;
import pl.travel360.dto.ActualWeatherDto;
import pl.travel360.exceptions.ResourcesNotProvide;
import pl.travel360.mapper.ActualWeatherMapper;
import pl.travel360.model.ActualWeather;
import pl.travel360.model.City;
import pl.travel360.provider.WeatherProvider;
import pl.travel360.repository.ActualWeatherRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ActualWeatherServices {
    private final ActualWeatherRepository weatherRepo;
    private final WeatherProvider weatherProvider;
    private final ActualWeatherMapper actualWeatherMapper;

    public void setActualWeather() {

        log.info(" get actual weather for all cities");
        List<ActualWeather> actualWeathers = weatherRepo.findAll();
        for (ActualWeather actualWeather : actualWeathers) {
            setActualWeatherForCity(actualWeather);
        }
    }

    public void addWeatherForCity(Long cityId) {
        ActualWeather actualWeather = ActualWeather.builder()
                .city(City.builder().id(cityId).build())
                .build();
        weatherRepo.save(actualWeather);
    }

    private void setActualWeatherForCity(ActualWeather actualWeatherOld) {
        log.info(" get actual weather for {}" , actualWeatherOld.getCity().getName());
        ActualWeather actualWeatherActual = new ActualWeather();
        try {
            actualWeatherActual = actualWeatherMapper.map(weatherProvider.getActualWeatherByCity(actualWeatherOld.getCity().getName()));
        } catch (Exception e) {
            log.warn("Not get weather for city {} with error {}", actualWeatherOld.getCity().getName(), e.getMessage());
        }
        updateWeather(actualWeatherOld, actualWeatherActual);

    }

    private void updateWeather(ActualWeather actualWeatherOld, ActualWeather actualWeatherActual) {
        try {
            actualWeatherActual.setDate(LocalDateTime.now());
            actualWeatherActual.setId(actualWeatherOld.getId());
            actualWeatherActual.setCity(actualWeatherOld.getCity());
            weatherRepo.save(actualWeatherActual);
        } catch (Exception e) {
            log.warn("Not save weather for city {} with error {}", actualWeatherOld.getCity().getName(), e.getMessage());
        }
    }


    public GetWeatherResponse getActualWeatherForCity(String cityName) {
        ActualWeatherDto actualWeatherDto = new ActualWeatherDto();
        try {
            actualWeatherDto = weatherProvider.getActualWeatherByCity(cityName);
        } catch (Exception e) {
            throw new ResourcesNotProvide("Actual weather for " + cityName + " not fund");
        }
        GetWeatherResponse weatherResponse = actualWeatherMapper.mapToGetResponse(actualWeatherDto);
        weatherResponse.setDate(LocalDateTime.now());
        weatherResponse.setCity(cityName);
        return weatherResponse;
    }

}
