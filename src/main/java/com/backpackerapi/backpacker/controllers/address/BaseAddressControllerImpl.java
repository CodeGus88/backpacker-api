package com.backpackerapi.backpacker.controllers.address;

import com.backpackerapi.backpacker.dtos.address.AddressDto;
import com.backpackerapi.backpacker.dtos.address.AddressRequest;
import com.backpackerapi.backpacker.models.address.BaseAddress;
import com.backpackerapi.backpacker.services.address.BaseAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.UUID;

public class BaseAddressControllerImpl<
        M extends BaseAddress,
        S extends BaseAddressService
        >
        implements BaseAddressController<M, S>{

    @Autowired
    protected S service;

    @GetMapping("{entityUuid}")
    @Override
    public ResponseEntity<List<AddressDto>> findAllByEntityUuid(@PathVariable UUID entityUuid) {
        return ResponseEntity.ok(service.findAllByEntityUuid(entityUuid));
    }

    @Override
    public ResponseEntity<AddressDto> create(AddressRequest address) {
        return ResponseEntity.ok(service.create(address));
    }

    @Override
    public ResponseEntity<AddressDto> update(@PathVariable UUID uuid, @RequestBody @Valid AddressRequest request) {
        return ResponseEntity.ok(service.update(uuid, request));
    }

    @Override
    public ResponseEntity<Void> deleteByUuid(UUID uuid) {
        service.deleteByUuid(uuid);
        if(!service.existsByUuid(uuid))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.internalServerError().build();
    }
}
