package com.backpackerapi.backpacker.controllers.address;

import com.backpackerapi.backpacker.dtos.address.AddressDto;
import com.backpackerapi.backpacker.dtos.address.AddressRequest;
import com.backpackerapi.backpacker.services.address.BaseAddressService;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface BaseAddressController<M, S extends BaseAddressService> {

    ResponseEntity<List<AddressDto>> findAllByEntityUuid(UUID entityUuid);

    ResponseEntity<AddressDto> save(AddressRequest request);

    ResponseEntity<AddressDto> update(UUID uuid, AddressRequest request);

    ResponseEntity deleteByUuid(UUID uuid);

}
