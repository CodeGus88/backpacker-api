package com.backpackerapi.backpacker.mappers;

import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceDto;
import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceRequest;
import com.backpackerapi.backpacker.mappers.address.TPAddressMapper;
import com.backpackerapi.backpacker.mappers.file.TouristPlaceFileMapper;
import com.backpackerapi.backpacker.models.TouristPlace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {TouristPlaceFileMapper.class, TPAddressMapper.class})
public interface TouristPlaceMapper {

    @Mappings({
            @Mapping(target = "uuid", ignore = true)
    })
    TouristPlace requestToEntity(TouristPlaceRequest touristPlaceRequest);

    TouristPlaceRequest dtoToRequest(TouristPlaceDto touristPlaceDto);

    TouristPlaceDto entityToDto(TouristPlace entity);
}
