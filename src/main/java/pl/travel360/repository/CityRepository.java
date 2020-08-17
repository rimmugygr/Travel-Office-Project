package pl.travel360.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.travel360.model.City;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Long> {
    Optional<City> getCityByName(String name);
}
