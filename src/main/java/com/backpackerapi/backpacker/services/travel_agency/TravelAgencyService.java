package com.backpackerapi.backpacker.services.travel_agency;

import com.backpackerapi.backpacker.dtos.travel_agency.ITravelAgencyItem;
import com.backpackerapi.backpacker.dtos.travel_agency.TravelAgencyDto;
import com.backpackerapi.backpacker.dtos.travel_agency.TravelAgencyRequest;
import com.backpackerapi.backpacker.services.BaseService;

import java.util.UUID;

public interface TravelAgencyService extends BaseService<UUID, ITravelAgencyItem, TravelAgencyRequest, TravelAgencyDto>{

    boolean existsByUuid(UUID uuid);

    boolean existsByUuidAndUserUsername(UUID uuid, String username);

}
