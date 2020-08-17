package pl.travel360.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.travel360.exceptions.ResourceNotFound;
import pl.travel360.model.City;
import pl.travel360.model.Offer;
import pl.travel360.model.User;
import pl.travel360.repository.OfferRepository;
import pl.travel360.repository.UserRepository;

import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class OfferServices {
    private final MailService mailService;
    private final OfferRepository offerRepo;
    private final CityService cityService;
    private final UserRepository userRepo;


    public void createOffer(Offer offerNew) {
        checkCityExistOrThrow(offerNew);
        Offer offer = offerRepo.save(offerNew);
        offer.setCity(cityService.findById(offerNew.getCity().getId()));
        mailToAllInterestedUser(offer);
    }

    private void checkCityExistOrThrow(Offer offerNew) {
        if (!cityService.iSCityExistsById(offerNew.getCity().getId()))
            throw new ResourceNotFound("City not found");
    }

    public void putOffer(Offer offerNew, Long offerId) {
        offerNew.setId(offerId);
        offerRepo.save(offerNew);
    }

    public List<Offer> getAllOffer() {
        return offerRepo.findAll();
    }

    public void mailToAllInterestedUser(Offer offer) {
        final String SUBJECT = "New offer for trip to: " + offer.getCity().getName();
        Set<User> interestedUser = userRepo.getAllByInterestedCityContains(offer.getCity());
        interestedUser.forEach( x -> {
            try {
                mailService.sendMail(x.getEmail(),SUBJECT, offer.getDescription(), false );
            } catch (MessagingException e) {
                log.warn("Not send message to: {}  with error {} ", x.getEmail(), e.getMessage());
            }
        });
    }
}
