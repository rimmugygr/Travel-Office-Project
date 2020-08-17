package pl.travel360.dto;


import lombok.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserByAdminDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Boolean enabled;
    private Set<String> userAuthorities = new HashSet<>();
    private List<Long> interestedCityId = new ArrayList<>();
}
