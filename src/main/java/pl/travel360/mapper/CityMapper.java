package pl.travel360.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.travel360.dto.CityDto;
import pl.travel360.model.City;

@Mapper(componentModel = "spring") //@Services
public interface CityMapper {

    @Mapping(source = "country.id", target = "countryId")
    CityDto map(City city);

    @Mapping(source = "countryId", target = "country.id")
    City map(CityDto cityDto);
}
