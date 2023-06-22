package com.backpackerapi.backpacker.controllers.rating;

import com.backpackerapi.backpacker.services.rating.TouristPlaceRatingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/tourist-places-rating")
@CrossOrigin(origins = "*")
public class TouristPlaceRatingController extends BaseRatingController<TouristPlaceRatingService> {

}
