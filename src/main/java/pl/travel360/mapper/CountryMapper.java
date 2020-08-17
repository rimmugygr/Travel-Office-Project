package pl.travel360.mapper;

import org.mapstruct.Mapper;
import pl.travel360.model.Country;
import pl.travel360.dto.CountryDto;

@Mapper(componentModel = "spring") //@Services
public interface CountryMapper {

    CountryDto map(Country country);
    Country map(CountryDto country);
}
