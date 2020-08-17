package pl.travel360.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.travel360.model.Authority;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    Optional<Authority> findByName(String name);


}
