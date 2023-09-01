package com.backpackerapi.backpacker.services.address.application;

import com.backpackerapi.backpacker.mappers.address.TravelAgencyAddressMapper;
import com.backpackerapi.backpacker.models.address.TravelAgencyAddress;
import com.backpackerapi.backpacker.repositories.address.TravelAgencyAddressRepository;
import com.backpackerapi.backpacker.services.address.BaseAddressService;


public interface TAAddressService extends BaseAddressService<TravelAgencyAddress, TravelAgencyAddressRepository, TravelAgencyAddressMapper> {


}
