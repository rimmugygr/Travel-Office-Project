package pl.travel360.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.travel360.client.GetActualWeatherApiResponse;
import pl.travel360.controller.response.dto.GetWeatherResponse;
import pl.travel360.dto.ActualWeatherDto;
import pl.travel360.model.ActualWeather;

@Mapper(componentModel = "spring") //@Services
public interface ActualWeatherMapper {

    @Mapping(source = "current.temperature", target = "temperature")
    @Mapping(source = "current.cloud", target = "cloud")
    @Mapping(source = "current.wind", target = "wind")
    @Mapping(source = "current.windDirection", target = "windDirection")
    ActualWeatherDto map(GetActualWeatherApiResponse getActualWeatherApiResponse);

    ActualWeather map(ActualWeatherDto actualWeatherDto);

    @Mapping(source = "city.name", target = "city")
    GetWeatherResponse mapToGetResponse(ActualWeatherDto actualWeatherDto);
}
