package com.backpackerapi.backpacker.services.address.application;

import com.backpackerapi.backpacker.mappers.address.TouristPlaceAddressMapper;
import com.backpackerapi.backpacker.models.address.TouristPlaceAddress;
import com.backpackerapi.backpacker.repositories.address.TPAddressRepository;
import com.backpackerapi.backpacker.services.address.BaseAddressService;


public interface TPAddressService extends BaseAddressService<TouristPlaceAddress, TPAddressRepository, TouristPlaceAddressMapper> {

}
