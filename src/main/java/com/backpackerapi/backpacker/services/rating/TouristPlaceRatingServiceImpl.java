package com.backpackerapi.backpacker.services.rating;
import com.backpackerapi.backpacker.mappers.rating.TouristPlaceRatingMapper;
import com.backpackerapi.backpacker.models.rating.TouristPlaceRating;
import com.backpackerapi.backpacker.repositories.rating.TouristPlaceRatingRepository;
import org.springframework.stereotype.Service;

@Service
public class TouristPlaceRatingServiceImpl extends BaseRatingServiceImpl<
        TouristPlaceRating, TouristPlaceRatingMapper, TouristPlaceRatingRepository> implements TouristPlaceRatingService{


}
