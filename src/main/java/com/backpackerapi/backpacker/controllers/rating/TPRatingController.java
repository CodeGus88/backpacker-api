package com.backpackerapi.backpacker.controllers.rating;

import com.backpackerapi.backpacker.dtos.rating.SimplePunctuationDto;
import com.backpackerapi.backpacker.enums.EEntity;
import com.backpackerapi.backpacker.services.rating.TouristPlaceRatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/tourist-places-rating")
@CrossOrigin(origins = "*")
public class TPRatingController extends BaseRatingController<TouristPlaceRatingService> {

    @GetMapping("punctuation/{entityUuid}")
    public ResponseEntity<SimplePunctuationDto> punctuationByEntityUuid(@PathVariable UUID entityUuid) {
        return ResponseEntity.ok(punctuationByEntityUuid(EEntity.TOURIST_PLACES_RATING, entityUuid));
    }
}
