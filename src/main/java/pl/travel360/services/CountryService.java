package pl.travel360.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.travel360.exceptions.ResourceNotFound;
import pl.travel360.model.Country;
import pl.travel360.repository.CountryRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public Collection<Country> getAllCountries(Integer minPopulation) {
        return countryRepository.findAllWithMinPopulation(minPopulation);
    }

    public void createCountry(Country country) {
        countryRepository.save(country);
    }

    public Country findByName(String countryName) {
        return countryRepository.findByName(countryName)
                .orElseThrow(() -> new ResourceNotFound("Country not found"));
    }

    public Country findById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Country not found"));
    }

    public boolean iSCountryExistsById(Long countryId) {
        return countryRepository.existsById(countryId);
    }

    public void deleteCountry(Long id) {
        Country country = findById(id);
        countryRepository.delete(country);
    }

    public void putCountry(Country countryEdited, Long id) {
        Country countryTarget = findById(id);
        countryEdited.setId(countryTarget.getId());
        countryRepository.save(countryEdited);
    }
}
