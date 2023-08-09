package com.backpackerapi.backpacker.services.address;

import com.backpackerapi.backpacker.dtos.address.AddressDto;
import com.backpackerapi.backpacker.dtos.address.AddressRequest;
import com.backpackerapi.backpacker.mappers.address.BaseAddressMapper;
import com.backpackerapi.backpacker.models.address.BaseAddress;
import com.backpackerapi.backpacker.repositories.address.BaseAddressRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface BaseAddressService
    <
        M extends BaseAddress,
        R extends BaseAddressRepository<M>,
        MAPPER extends BaseAddressMapper<M>
    > {

    List<M> findAllByEntityUuid(UUID entityUuid);

    @Transactional
    AddressDto create(AddressRequest request);

    @Transactional
    AddressDto update(UUID uuid, AddressRequest request);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

}
