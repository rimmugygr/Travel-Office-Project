package pl.travel360.countries;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.travel360.controller.CountryController;
import pl.travel360.dto.CountryDto;
import pl.travel360.mapper.CountryMapper;
import pl.travel360.model.Country;
import pl.travel360.services.CountryService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;


@WebMvcTest(controllers = {CountryController.class})
class CountryControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    CountryService mockService;

    @MockBean
    CountryMapper mockMapper;

    @Test
    //@Disabled
    @WithMockUser
    //@WithAnonymousUser
    void shouldEditCountryWhenChangedProvided() throws Exception {
        //given
        Long countryId = 1L;

        var countryToEditDto = CountryDto.builder()
                .name("USA")
                .capital("New York")
                .currency("USD")
                .language("English")
                .population(10000)
                .build();
        var countryToEdit = Country.builder()
                .name("USA")
                .capital("New York")
                .currency("USD")
                .language("English")
                .population(10000)
                .build();

        var editRequestCountryJson = mapper.writeValueAsString(countryToEditDto);
        //when
        ResultActions result = mvc.perform(
                MockMvcRequestBuilders.put("/countries/{countryId}", countryId)
                       // .with(user("user"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(editRequestCountryJson));

        //then
        result.andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(mockService).putCountry(countryToEdit,countryId);
    }
}
