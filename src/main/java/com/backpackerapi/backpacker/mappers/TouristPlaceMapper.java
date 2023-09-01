package com.backpackerapi.backpacker.mappers;

import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceDto;
import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceRequest;
import com.backpackerapi.backpacker.mappers.address.TouristPlaceAddressMapper;
import com.backpackerapi.backpacker.mappers.file.TouristPlaceFileMapper;
import com.backpackerapi.backpacker.models.principal_models.TouristPlace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {TouristPlaceFileMapper.class, TouristPlaceAddressMapper.class})
public interface TouristPlaceMapper {

    @Mappings({
            @Mapping(target = "uuid", ignore = true),
            @Mapping(target = "categories", ignore = true)
    })
    TouristPlace requestToEntity(TouristPlaceRequest touristPlaceRequest);

    @Mappings({
            @Mapping(target = "categories", ignore = true)
    })
    TouristPlaceRequest dtoToRequest(TouristPlaceDto touristPlaceDto);

    TouristPlaceDto entityToDto(TouristPlace entity);
}
