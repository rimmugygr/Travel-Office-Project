package pl.travel360.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.travel360.dto.UserByUserDto;
import pl.travel360.exceptions.ResourceNotFound;
import pl.travel360.mapper.UserMapper;
import pl.travel360.model.User;
import pl.travel360.repository.UserRepository;

@AllArgsConstructor
@Service
public class UserByUserServices {
    private final UserRepository userRepo;
    private final UserMapper userMapper;

    public UserByUserDto getUserByUsername(String username) {
        User user = userRepo.getOneByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return userMapper.mapToUserByUserDto(user);
    }

    public void patchUserByUsername(String username, UserByUserDto userByUserDto) {
        User userDB = userRepo.getOneByUsername(username)
                .orElseThrow(() -> new ResourceNotFound("User not found"));
        checkDataToUpdate(userByUserDto, userDB);
        userRepo.save(userDB);
    }

    private void checkDataToUpdate(UserByUserDto userByUserDtoInput, User userDB) {
        User userNew = userMapper.map(userByUserDtoInput);
        if(userByUserDtoInput.getFirstName()!=null)
            userDB.setFirstName(userNew.getFirstName());
        if(userByUserDtoInput.getLastName()!=null)
            userDB.setLastName(userNew.getLastName());
        if(userByUserDtoInput.getEmail()!=null)
            userDB.setEmail(userNew.getEmail());
        if(userByUserDtoInput.getInterestedCityId()!=null)
            userDB.setInterestedCity(userNew.getInterestedCity());
        if(userByUserDtoInput.getPassword()!=null)
            userDB.setPassword(userNew.getPassword());
    }

    public void deleteUserByUsername(String username) {
        userRepo.deleteByUsername(username);
    }
}
