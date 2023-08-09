package com.backpackerapi.backpacker.controllers.address;

import com.backpackerapi.backpacker.dtos.address.AddressDto;
import com.backpackerapi.backpacker.dtos.address.AddressRequest;
import com.backpackerapi.backpacker.models.address.address.TPAddress;
import com.backpackerapi.backpacker.services.address.TPAddressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RequestMapping("api/tp-addresses")
@RestController
@CrossOrigin
public class TPAddressControllerImpl extends BaseAddressControllerImpl<TPAddress, TPAddressService>
implements TPAddressController {

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Override
    public ResponseEntity<AddressDto> create(@RequestBody @Valid AddressRequest address) {
        return super.create(address);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{uuid}")
    @Override
    public ResponseEntity<AddressDto> update(@PathVariable UUID uuid, @RequestBody @Valid AddressRequest request) {
        return super.update(uuid, request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{uuid}")
    @Override
    public ResponseEntity<Void> deleteByUuid(@PathVariable UUID uuid) {
        return super.deleteByUuid(uuid);
    }
}
