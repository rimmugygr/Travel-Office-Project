package pl.travel360.dto;


import lombok.*;
import pl.travel360.model.Authority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserByUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Long> interestedCityId = new ArrayList<>();
}
