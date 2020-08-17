package pl.travel360.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "of")
@Builder
@Setter
@Getter
public class OfferDto {
    private Long id;
    private Long cityId;
    private Long price;
    private String name;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
    private Long day;
}
