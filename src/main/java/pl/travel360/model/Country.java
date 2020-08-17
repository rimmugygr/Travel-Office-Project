package pl.travel360.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String capital;
    private String language;
    private String currency;
    private int population;
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "country",
            cascade = CascadeType.ALL
    )
    private List<City> cities = new ArrayList<>();
}
