package com.backpackerapi.backpacker.services.address;

import com.backpackerapi.backpacker.mappers.address.TPAddressMapper;
import com.backpackerapi.backpacker.models.address.address.TPAddress;
import com.backpackerapi.backpacker.repositories.address.TPAddressRepository;
import org.springframework.stereotype.Service;

@Service
public class TPAddressServiceImpl extends BaseAddressServiceImpl<TPAddress, TPAddressRepository, TPAddressMapper>
    implements TPAddressService
{

}
