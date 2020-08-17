package pl.travel360.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.travel360.dto.DayWeatherDto;
import pl.travel360.exceptions.ResourcesNotProvide;
import pl.travel360.mapper.DayWeatherMapper;
import pl.travel360.model.City;
import pl.travel360.model.DayWeather;
import pl.travel360.model.WeatherStatus;
import pl.travel360.provider.WeatherProvider;
import pl.travel360.repository.DayWeatherRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class DayWeatherServices {
    private final WeatherProvider weatherProvider;
    private final DayWeatherRepository dayWeatherRepository;
    private final DayWeatherMapper dayWeatherMapper;

    public List<DayWeatherDto> getDayWeatherForCity(String cityName) {
        List<DayWeatherDto> dayWeatherDto = new ArrayList<>();
        try {
            dayWeatherDto = weatherProvider.getDayWeatherByCity(cityName);
        } catch (Exception e) {
            throw new ResourcesNotProvide("Day weather for " + cityName + " not fund" + e.getMessage());
        }
        return dayWeatherDto;
    }

    public void updateDailyWeatherForCities() {

        log.info("################### START  get daily weather for all cities" );
        List<DayWeather> dayWeathers = dayWeatherRepository.findAll();

        Map<City, List<DayWeather>> cityDayWeatherMap = dayWeathers.stream()
                .collect(Collectors.groupingBy(DayWeather::getCity));

        for (Map.Entry<City, List<DayWeather>> cityListEntry : cityDayWeatherMap.entrySet()) {

            City city = cityListEntry.getKey();
            List<DayWeather> dailyWeathers = cityListEntry.getValue();

            updateDailyWeatherForCity(city, dailyWeathers);
        }
        log.info("################### END get daily weather for all cities" );
    }

    private void updateDailyWeatherForCity(City city, List<DayWeather> dailyWeathersOld) {

        log.info(" Get daily weather for {}" , city.getName());
        List<DayWeatherDto> dayWeatherDtoByCity = new ArrayList<>();
        try {
            dayWeatherDtoByCity = weatherProvider.getDayWeatherByCity(city.getName());
        } catch (Exception e) {
            log.warn("Daily weather for {} not fund {}" , city.getName(), e.getMessage());
        }
        List<DayWeather> dayWeathersNew = dayWeatherDtoByCity.stream()
                .map(dayWeatherMapper::map)
                .collect(Collectors.toList());

        saveUpdatedDailyWeather(dailyWeathersOld, dayWeathersNew);

    }

    private void saveUpdatedDailyWeather(List<DayWeather> dailyWeathersOld, List<DayWeather> dayWeathersNew) {
        try {
            int i = 0;
            for (DayWeather dayWeatherNew : dayWeathersNew) {
                DayWeather dayWeatherOld = dailyWeathersOld.get(i++);

                log.info("get daily weather for {}" , dayWeatherOld.getWeatherStatus());
                dayWeatherNew.setId(dayWeatherOld.getId());
                dayWeatherNew.setCity(dayWeatherOld.getCity());
                dayWeatherNew.setWeatherStatus(dayWeatherOld.getWeatherStatus());

                dayWeatherRepository.save(dayWeatherNew);
            }
        } catch (Exception e) {
            log.warn("Daily weather for  not save {}" , e.getMessage());
        }
    }

    public void addDailyWeatherForCity(Long cityId) {
        List<DayWeather> dayWeatherList = Arrays.stream(WeatherStatus.values())
                .map(x -> DayWeather.builder()
                        .city(City.builder().id(cityId).build())
                        .weatherStatus(x)
                        .build())
                .collect(Collectors.toList());
        dayWeatherRepository.saveAll(dayWeatherList);
    }
}
