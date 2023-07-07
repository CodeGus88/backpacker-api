package com.backpackerapi.backpacker.mappers.address;

import com.backpackerapi.backpacker.models.address.address.TPAddress;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TPAddressMapper extends BaseAddressMapper<TPAddress>{



}
