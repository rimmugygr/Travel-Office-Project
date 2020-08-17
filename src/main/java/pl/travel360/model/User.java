package pl.travel360.model;


import lombok.*;

import javax.persistence.*;
import java.util.*;


@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    @Column(name = "username", unique = true)
    private String username;
    private String password;
    private Boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_name",referencedColumnName = "name"))
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Authority> userAuthorities = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_city_interest",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "city_id", referencedColumnName = "id"))
    private Set<City> interestedCity = new HashSet<>();


    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.password = user.password;
        this.enabled = user.enabled;
        this.userAuthorities = user.userAuthorities;
    }

}
