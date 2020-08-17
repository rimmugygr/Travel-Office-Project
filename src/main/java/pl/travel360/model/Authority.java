package pl.travel360.model;


import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Authority {
    @Id
    @Column(name = "name", unique = true)
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userAuthorities")
    private Collection<User> users;
//    @ManyToOne
//    private User user;
}
