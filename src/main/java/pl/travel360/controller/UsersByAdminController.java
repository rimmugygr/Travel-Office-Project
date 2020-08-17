package pl.travel360.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.travel360.dto.UserByAdminDto;
import pl.travel360.services.UserByAdminServices;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping(
        value = "/v1/admin/user",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class UsersByAdminController {
    private final UserByAdminServices userByAdminServices;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserByAdminDto> getAllUsers() {
        return userByAdminServices.getAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserByAdminDto getUser(@PathVariable Long id) {
        return userByAdminServices.getUserById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void patchUser(@PathVariable Long id, @RequestBody UserByAdminDto userByAdminDto) {
        userByAdminServices.patchUserById(id, userByAdminDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postUser(@RequestBody UserByAdminDto userByAdminDto) {
        userByAdminServices.addUser(userByAdminDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteUser(@PathVariable Long id) {
        userByAdminServices.deleteUserById(id);
    }

}
