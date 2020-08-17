package pl.travel360.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.travel360.dto.UserByAdminDto;
import pl.travel360.dto.UserByUserDto;
import pl.travel360.model.Authority;
import pl.travel360.model.City;
import pl.travel360.model.User;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    @Mapping(source = "interestedCityId",target = "interestedCity")
    User map(UserByAdminDto userByAdminDto);
    @Mapping(source = "interestedCity",target = "interestedCityId")
    UserByAdminDto mapToUserByAdminDto(User user);

    @Mapping(source = "interestedCityId",target = "interestedCity")
    User map(UserByUserDto userByUserDto);
    @Mapping(source = "interestedCity",target = "interestedCityId")
    UserByUserDto mapToUserByUserDto(User user);


    default City mapLongToCity(Long l) {
        return City.builder().id(l).build();
    }
    default Long mapCityToLong(City city) {
        return city.getId();
    }

    default Authority mapStringToAuthority(String s) {
        return Authority.builder().name(s).build();
    }
    default String mapAuthorityToString(Authority authority) {
        return authority.getName();
    }


}
