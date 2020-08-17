package pl.travel360.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.travel360.model.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}
