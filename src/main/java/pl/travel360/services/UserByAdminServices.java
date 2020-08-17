package pl.travel360.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.travel360.dto.UserByAdminDto;
import pl.travel360.exceptions.AuthoritiesNotFoundException;
import pl.travel360.exceptions.ResourceNotFound;
import pl.travel360.exceptions.UsernameAlreadyExistException;
import pl.travel360.mapper.UserMapper;
import pl.travel360.model.User;
import pl.travel360.repository.AuthorityRepository;
import pl.travel360.repository.CityRepository;
import pl.travel360.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserByAdminServices {
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final UserMapper userMapper;
    private final AuthorityRepository authorityRepo;

    public List<UserByAdminDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::mapToUserByAdminDto).collect(Collectors.toList());
    }

    public void addUser(UserByAdminDto userByAdminDto) {
        User user = userMapper.map(userByAdminDto);

        user.getUserAuthorities()
                .forEach(x -> authorityRepo.findByName(x.getName())
                        .orElseThrow(() -> new AuthoritiesNotFoundException("Authorities  not found")));

        if (userRepository.findUserByUsername(user.getUsername()) != null)
            throw new UsernameAlreadyExistException("Username exist");

        userRepository.save(user);
    }

    public UserByAdminDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found"));
        return userMapper.mapToUserByAdminDto(user);
    }



    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void patchUserById(Long id, UserByAdminDto userByAdminDto) {
        User userNew = userMapper.map(userByAdminDto);
        User userOld = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found"));
        if(userNew.getFirstName()!=null)
            userOld.setFirstName(userNew.getFirstName());
        if(userNew.getLastName()!=null)
            userOld.setLastName(userNew.getLastName());
        if(userNew.getEmail()!=null)
            userOld.setEmail(userNew.getEmail());
        if(userNew.getInterestedCity()!=null && !userNew.getInterestedCity().isEmpty())
            userOld.setInterestedCity(userNew.getInterestedCity());
        userRepository.save(userOld);
    }


}
