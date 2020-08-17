package pl.travel360.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.travel360.exceptions.ResourceNotFound;
import pl.travel360.model.City;
import pl.travel360.repository.CityRepository;

import java.util.Collection;

@AllArgsConstructor
@Service
public class CityService {
    private final CityRepository cityRepo;
    private final CountryService countryService;
    private final ActualWeatherServices actualWeatherServices;
    private final DayWeatherServices dayWeatherServices;

    public Collection<City> getAllCities() {
        return cityRepo.findAll();
    }

    public City findByName(String cityName) {
        return cityRepo.getCityByName(cityName)
                .orElseThrow(() -> new ResourceNotFound("City not found"));
    }

    public boolean iSCityExistsById(Long cityId) {
        return cityRepo.existsById(cityId);
    }

    public City findById(Long cityId) {
        return cityRepo.findById(cityId)
                .orElseThrow(() -> new ResourceNotFound("City not found"));
    }


    public void createCity(City city) {
        checkCountryExistOrThrow(city);

        cityRepo.save(city);
        actualWeatherServices.addWeatherForCity(city.getId());
        dayWeatherServices.addDailyWeatherForCity(city.getId());
    }

    private void checkCountryExistOrThrow(City city) {
        if (!countryService.iSCountryExistsById(city.getCountry().getId()))
            throw new ResourceNotFound("Country not found");
    }

    public void deleteCity(Long cityId) {
        City city = findById(cityId);

        cityRepo.delete(city);
    }

    public void putCity(City cityInput, Long cityId) {
        City cityTarget = findById(cityId);

        cityInput.setId(cityTarget.getId());

        cityRepo.save(cityInput);
    }
}
