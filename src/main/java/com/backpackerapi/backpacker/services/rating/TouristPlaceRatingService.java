package com.backpackerapi.backpacker.services.rating;

import com.backpackerapi.backpacker.mappers.rating.TouristPlaceRatingMapper;
import com.backpackerapi.backpacker.models.rating.TouristPlaceRating;
import com.backpackerapi.backpacker.repositories.rating.TouristPlaceRatingRepository;

public interface TouristPlaceRatingService extends BaseRatingService<
        TouristPlaceRating,
        TouristPlaceRatingMapper,
        TouristPlaceRatingRepository
        > {
//    Métodos propiod de tpratingservice
}
