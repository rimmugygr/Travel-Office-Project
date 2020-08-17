package pl.travel360.countries;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import pl.travel360.repository.CountryRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

@Sql(scripts = "classpath:country-repository-test.sql")
@DataJpaTest
class CountryRepositoryTest {

    @Autowired
    CountryRepository repository;

    @Test
    void shouldShowCountriesWithPopulationAboveGivenLimit() {
        //given
        var populationLimit = 15000001;

        //when
        var result = repository.findAllWithMinPopulation(populationLimit);

        //then
        assertThat(result, notNullValue());

        assertThat(result, hasSize(2));
    }
}
