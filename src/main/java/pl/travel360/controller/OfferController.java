package pl.travel360.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.travel360.controller.response.dto.GetOfferResponse;
import pl.travel360.dto.OfferDto;
import pl.travel360.mapper.OfferMapper;
import pl.travel360.model.Offer;
import pl.travel360.services.OfferServices;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.noContent;

@AllArgsConstructor
@RequestMapping(path = "/v1/offer",
        produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class OfferController {
    private final OfferMapper offerMapper;
    private final OfferServices offerServices;

    @PostMapping
    public ResponseEntity<Void> createOffer(@RequestBody OfferDto newOfferDto) {
        offerServices.createOffer(  offerMapper.map(newOfferDto));
        return noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putOffer(@PathVariable Long id,
                                         @RequestBody OfferDto newOfferDto) {
        offerServices.putOffer(  offerMapper.map(newOfferDto) , id);
        return noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<GetOfferResponse>> getOffer() {
        List<Offer> allOffer = offerServices.getAllOffer();
        List<GetOfferResponse> allOfferResponse = allOffer.stream()
                .map(offerMapper::map)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(allOfferResponse);
    }
}
