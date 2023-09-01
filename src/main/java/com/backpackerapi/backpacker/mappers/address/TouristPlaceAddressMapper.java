package com.backpackerapi.backpacker.mappers.address;

import com.backpackerapi.backpacker.models.address.TouristPlaceAddress;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TouristPlaceAddressMapper extends BaseAddressMapper<TouristPlaceAddress>{



}
