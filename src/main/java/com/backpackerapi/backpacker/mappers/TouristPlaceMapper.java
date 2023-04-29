package com.backpackerapi.backpacker.mappers;

import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceDto;
import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceRequest;
import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceItem;
import com.backpackerapi.backpacker.models.TouristPlace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TouristPlaceMapper extends BaseMapper<TouristPlaceRequest, TouristPlaceItem, TouristPlaceDto, TouristPlace> {

    @Mappings({
            @Mapping(target = "uuid", ignore = true)
    })
    @Override
    TouristPlace requestToEntity(TouristPlaceRequest touristPlaceRequest);

}
