package com.backpackerapi.backpacker.mappers.rating;

import com.backpackerapi.backpacker.models.rating.TouristPlaceRating;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TouristPlaceRatingMapper extends BaseRatingMapper<TouristPlaceRating> {

}
