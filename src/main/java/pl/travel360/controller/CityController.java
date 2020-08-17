package pl.travel360.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.travel360.dto.CityDto;
import pl.travel360.mapper.CityMapper;
import pl.travel360.services.CityService;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@AllArgsConstructor
@RequestMapping(path = "/v1/cities",
        produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class CityController {

    private final CityService service;

    private final CityMapper mapper;

    @GetMapping
    public ResponseEntity<Collection<CityDto>> getCity() {
        Collection<CityDto> cities = service.getAllCities().stream()
                .map(mapper::map)
                .collect(Collectors.toList());
        return ok().body(cities);
    }

    @GetMapping("/{id}")
    public CityDto getCityById(@PathVariable Long id) {
        return mapper.map(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createCountry(@RequestBody CityDto newCountry) {
        service.createCity(  mapper.map(newCountry));
        return noContent().build();
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable Long id) {
        service.deleteCity(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> editCountry(@PathVariable Long id,
                                            @RequestBody CityDto cityDto) {
        service.putCity(mapper.map(cityDto),id);
        return ResponseEntity.noContent().build();
    }
}
