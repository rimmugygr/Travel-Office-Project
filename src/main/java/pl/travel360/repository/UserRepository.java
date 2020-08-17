package pl.travel360.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.travel360.model.City;
import pl.travel360.model.User;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getOneByUsername(String username);
    User findUserByUsername(String username);
    void deleteByUsername(String username);
    Set<User> getAllByInterestedCityContains(City city);

}
