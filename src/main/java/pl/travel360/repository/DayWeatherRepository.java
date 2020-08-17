package pl.travel360.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.travel360.model.DayWeather;

public interface DayWeatherRepository extends JpaRepository<DayWeather, Long> {


}
