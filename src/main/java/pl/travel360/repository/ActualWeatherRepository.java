package pl.travel360.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.travel360.model.ActualWeather;

public interface ActualWeatherRepository extends JpaRepository<ActualWeather, Long> {


}
