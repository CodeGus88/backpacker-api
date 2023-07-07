package com.backpackerapi.backpacker.mappers.address;

import com.backpackerapi.backpacker.dtos.address.AddressDto;
import com.backpackerapi.backpacker.dtos.address.AddressRequest;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

public interface BaseAddressMapper<M> {

//    @Mappings(
//        {
//            @Mapping(source = "entity.uuid", target = "entityUuid"),
////            @Mapping(source = "region.Uuid", target = "regionUuid")
//        }
//    )
    AddressDto entityToDto(M object);

    @Mappings(
            {
                    @Mapping(source = "entityUuid", target = "entity.uuid")
            }
    )
    M requestToModel(AddressRequest request);

}
