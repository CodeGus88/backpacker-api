package com.backpackerapi.backpacker.mappers.address;

import com.backpackerapi.backpacker.models.address.TravelAgencyAddress;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TravelAgencyAddressMapper extends BaseAddressMapper<TravelAgencyAddress>{


}
