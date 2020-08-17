package pl.travel360.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.travel360.dto.CountryDto;
import pl.travel360.mapper.CountryMapper;
import pl.travel360.services.CountryService;
import java.util.Collection;
import java.util.stream.Collectors;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@AllArgsConstructor
@RequestMapping(path = "/v1/countries",
        produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class CountryController {
    private final CountryService service;
    private final CountryMapper mapper;

    @ApiOperation(value = "Returns all countries with optional minimum population filter", httpMethod = "GET")
    @ApiResponse(code = 200, message = "Get all countries succeeded")
    @GetMapping
    public ResponseEntity<Collection<CountryDto>> getCountries(
            @RequestParam(name = "minPopulation", defaultValue = "0") Integer minPopulation) {
        var countries = service.getAllCountries(minPopulation).stream()
                .map(mapper::map)
                .collect(Collectors.toList());
        return ok().body(countries);
    }

    @ApiOperation(value = "Returns a specific country with given id", httpMethod = "GET")
    @ApiResponse(code = 200, message = "Get a specific country succeeded")
    @GetMapping("/{id}")
    public CountryDto getCountryById(@PathVariable Long id) {
        return mapper.map(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createCountry(@RequestBody CountryDto newCountry) {
        service.createCountry(  mapper.map(newCountry));
        return noContent().build();
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable Long id) {
        service.deleteCountry(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editCountry(@PathVariable Long id,
                                            @RequestBody CountryDto editedCountry) {
        service.putCountry(mapper.map(editedCountry),id);
        return ResponseEntity.noContent().build();
    }
}
