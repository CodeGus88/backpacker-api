package com.backpackerapi.backpacker.services.address;

import com.backpackerapi.backpacker.dtos.address.AddressDto;
import com.backpackerapi.backpacker.dtos.address.AddressRequest;
import com.backpackerapi.backpacker.exceptions.CustomException;
import com.backpackerapi.backpacker.mappers.address.BaseAddressMapper;
import com.backpackerapi.backpacker.models.address.BaseAddress;
import com.backpackerapi.backpacker.repositories.address.BaseAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

public class BaseAddressServiceImpl<
        M extends BaseAddress,
        R extends BaseAddressRepository<M>,
        MAPPER extends BaseAddressMapper<M>
        >
        implements BaseAddressService<M, R, MAPPER>{

    @Autowired
    protected R repository;

    @Autowired
    private MAPPER mapper;

    @Override
    public M findById(UUID uuid) {
        return repository.findById(uuid).orElse(null);
    }

    @Override
    public List<M> findAllByEntityUuid(UUID entityUuid) {
        List<M> list = repository.findByEntityUuid(entityUuid);
        return list;
    }

    @Override
    public AddressDto create(AddressRequest request) {
        M m = mapper.requestToModel(request);
        m.setUuid(UUID.randomUUID());
        AddressDto addressDto = mapper.entityToDto(
                repository.save(m)
        );
        return addressDto;
    }

    @Override
    public AddressDto update(UUID uuid, AddressRequest request) {
        if(!repository.existsById(uuid))
            throw new CustomException(HttpStatus.NOT_FOUND, "No se encontró el recurso");
        M m = mapper.requestToModel(request);
        m.setUuid(uuid);
        AddressDto addressDto = mapper.entityToDto(
                repository.save(m)
        );
        return addressDto;
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        repository.deleteById(uuid);
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return repository.existsById(uuid);
    }



}
