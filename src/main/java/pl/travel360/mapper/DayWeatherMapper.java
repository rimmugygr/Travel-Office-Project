package pl.travel360.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.travel360.client.DayWeatherResponse;
import pl.travel360.dto.DayWeatherDto;
import pl.travel360.model.DayWeather;

@Mapper(componentModel = "spring") //@Services
public interface DayWeatherMapper {

    @Mapping(source = "date", target = "date")
    @Mapping(source = "day.temperature", target = "temperature")
    @Mapping(source = "day.wind", target = "wind")
    @Mapping(source = "day.precipitation", target = "precipitation")
    @Mapping(source = "day.humidity", target = "humidity")
    @Mapping(source = "day.description.text", target = "description")
    DayWeatherDto map(DayWeatherResponse dayWeatherResponse);

    DayWeather map(DayWeatherDto dayWeatherDto);

}
