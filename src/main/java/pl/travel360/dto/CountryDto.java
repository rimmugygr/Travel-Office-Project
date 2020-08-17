package pl.travel360.dto;

import lombok.*;

@Data
@AllArgsConstructor(staticName = "of")
@Builder
public class CountryDto {
    private Long id;
    private String name;
    private String capital;
    private String language;
    private String currency;
    private int population;
}
