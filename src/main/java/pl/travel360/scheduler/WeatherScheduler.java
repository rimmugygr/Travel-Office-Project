package pl.travel360.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.travel360.services.DayWeatherServices;
import pl.travel360.services.ActualWeatherServices;

@Slf4j
@Service
@AllArgsConstructor
public class WeatherScheduler {
    private final ActualWeatherServices actualWeatherServices;
    private final DayWeatherServices dayWeatherServices;

    @Scheduled(cron = "0 */15 * * * *")
    private void fetchActualWeather() {
        actualWeatherServices.setActualWeather();
    }

    @Scheduled(cron = "0 */30 * * * *")
    private void fetchDailyWeather() {
        dayWeatherServices.updateDailyWeatherForCities();
    }
}
