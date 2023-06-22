package com.backpackerapi.backpacker.controllers.tourist_place;

import com.backpackerapi.backpacker.controllers.BaseController;
import com.backpackerapi.backpacker.dtos.tourist_place.ITouristPlaceItem;
import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceDto;
import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceRequest;
import java.util.UUID;

public interface TouristPlaceController extends BaseController<UUID, ITouristPlaceItem, TouristPlaceRequest, TouristPlaceDto> {


}
