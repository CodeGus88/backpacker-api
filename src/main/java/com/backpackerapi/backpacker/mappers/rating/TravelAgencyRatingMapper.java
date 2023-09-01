package com.backpackerapi.backpacker.mappers.rating;

import com.backpackerapi.backpacker.models.rating.TravelAgencyRating;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TravelAgencyRatingMapper extends BaseRatingMapper<TravelAgencyRating> {

}
