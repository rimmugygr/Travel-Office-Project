package pl.travel360.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.travel360.controller.response.dto.GetWeatherResponse;
import pl.travel360.dto.DayWeatherDto;
import pl.travel360.services.ActualWeatherServices;
import pl.travel360.services.DayWeatherServices;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(
        value = "/v1/weather",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class WeatherController {
    private final ActualWeatherServices actualWeatherServices;
    private final DayWeatherServices dayWeatherServices;

    @GetMapping("/actual/{cityName}")
    @ResponseStatus(HttpStatus.OK)
    public GetWeatherResponse getActualWeather(@PathVariable(value = "cityName") String cityName){
        return actualWeatherServices.getActualWeatherForCity(cityName);
    }

    @GetMapping("/daily/{cityName}")
    @ResponseStatus(HttpStatus.OK)
    public List<DayWeatherDto> getDailyWeather(@PathVariable(value = "cityName") String cityName){
        return dayWeatherServices.getDayWeatherForCity(cityName);
    }

}
