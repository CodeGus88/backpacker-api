package com.backpackerapi.backpacker.mappers;

import com.backpackerapi.backpacker.dtos.travel_agency.TravelAgencyDto;
import com.backpackerapi.backpacker.dtos.travel_agency.TravelAgencyRequest;
import com.backpackerapi.backpacker.mappers.address.TravelAgencyAddressMapper;
import com.backpackerapi.backpacker.mappers.file.TravelAgencyFileMapper;
import com.backpackerapi.backpacker.models.principal_models.TravelAgency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {TravelAgencyFileMapper.class, TravelAgencyAddressMapper.class})
public interface TravelAgencyMapper {

    @Mappings({
            @Mapping(target = "uuid", ignore = true),
            @Mapping(source = "userUuid", target = "user.uuid")
    })
    TravelAgency requestToEntity(TravelAgencyRequest request);

    TravelAgencyRequest dtoToRequest(TravelAgencyDto dto);

    TravelAgencyDto entityToDto(TravelAgency entity);
}
