package pl.travel360.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
@Builder
public class CityDto {
    private Long id;
    private String name;
    private Long countryId;
}
