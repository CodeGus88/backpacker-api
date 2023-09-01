package com.backpackerapi.backpacker.services.address.application;

import com.backpackerapi.backpacker.mappers.address.TravelAgencyAddressMapper;
import com.backpackerapi.backpacker.models.address.TravelAgencyAddress;
import com.backpackerapi.backpacker.repositories.address.TravelAgencyAddressRepository;
import com.backpackerapi.backpacker.services.address.BaseAddressServiceImpl;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TAAddressServiceImpl
        extends BaseAddressServiceImpl<TravelAgencyAddress, TravelAgencyAddressRepository, TravelAgencyAddressMapper>
        implements TAAddressService {

    @Override
    public TravelAgencyAddress findById(UUID uuid) {
        return repository.findById(uuid).orElse(null);
    }
}
