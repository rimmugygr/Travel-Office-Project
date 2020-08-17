package pl.travel360.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.travel360.config.CurrentUsername;
import pl.travel360.dto.UserByUserDto;
import pl.travel360.services.UserByUserServices;


@AllArgsConstructor
@RestController
@RequestMapping(
        value = "/v1/user",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserByUserController {
    private final UserByUserServices userByUserServices;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserByUserDto getLoggedUser(@CurrentUsername String username) {
        return userByUserServices.getUserByUsername(username);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void patchUser(@CurrentUsername String username, @RequestBody UserByUserDto userByUserDto) {
        userByUserServices.patchUserByUsername(username, userByUserDto);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteUser(@CurrentUsername String username) {
        userByUserServices.deleteUserByUsername(username);
    }

}
