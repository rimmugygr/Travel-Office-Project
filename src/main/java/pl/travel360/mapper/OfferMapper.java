package pl.travel360.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.travel360.controller.response.dto.GetOfferResponse;
import pl.travel360.dto.OfferDto;
import pl.travel360.model.ActualWeather;
import pl.travel360.model.Offer;

@Mapper(componentModel = "spring") //@Services
public interface OfferMapper {

    @Mapping(source = "cityId", target = "city.id")
    Offer map(OfferDto offerDto);

    @Mapping(source = "city.weather", target = "weather")
    @Mapping(source = "city.country.name", target = "countryName")
    @Mapping(source = "city.name", target = "cityName")
    GetOfferResponse map(Offer offer);

    GetOfferResponse.WeatherResponse map(ActualWeather actualWeather);

}
