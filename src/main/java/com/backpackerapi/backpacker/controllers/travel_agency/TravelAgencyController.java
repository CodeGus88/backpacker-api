package com.backpackerapi.backpacker.controllers.travel_agency;

import com.backpackerapi.backpacker.controllers.BaseController;
import com.backpackerapi.backpacker.dtos.travel_agency.ITravelAgencyItem;
import com.backpackerapi.backpacker.dtos.travel_agency.TravelAgencyDto;
import com.backpackerapi.backpacker.dtos.travel_agency.TravelAgencyRequest;

import java.util.UUID;

public interface TravelAgencyController extends BaseController<UUID, ITravelAgencyItem, TravelAgencyRequest, TravelAgencyDto> {


}
