package com.backpackerapi.backpacker.services.tourist_place;

import com.backpackerapi.backpacker.dtos.tourist_place.ITouristPlaceItem;
import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceDto;
import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceRequest;
import com.backpackerapi.backpacker.services.BaseService;

import java.util.UUID;

public interface TouristPlaceService extends BaseService<UUID, ITouristPlaceItem, TouristPlaceRequest, TouristPlaceDto> {

    long count();

    boolean existById(UUID uuid);

}
