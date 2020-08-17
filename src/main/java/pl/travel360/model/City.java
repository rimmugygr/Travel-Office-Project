package pl.travel360.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Country country;
    @OneToOne(fetch = FetchType.LAZY,
            mappedBy = "city",
            cascade = CascadeType.ALL)
    private ActualWeather weather;
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "city",
            cascade = CascadeType.ALL)
    private List<DayWeather> dayWeathers = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "city",
            cascade = CascadeType.ALL)
    private List<Offer> offerList = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "interestedCity")
    private Set<User> interestedUser = new HashSet<>();

}
