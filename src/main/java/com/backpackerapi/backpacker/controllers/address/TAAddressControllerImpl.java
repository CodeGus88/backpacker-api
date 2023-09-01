package com.backpackerapi.backpacker.controllers.address;

import com.backpackerapi.backpacker.dtos.address.AddressDto;
import com.backpackerapi.backpacker.dtos.address.AddressRequest;
import com.backpackerapi.backpacker.exceptions.CustomException;
import com.backpackerapi.backpacker.models.address.TravelAgencyAddress;
import com.backpackerapi.backpacker.services.address.application.TAAddressService;
import com.backpackerapi.backpacker.services.travel_agency.TravelAgencyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RequestMapping("api/ta-addresses")
@RestController
@CrossOrigin
public class TAAddressControllerImpl extends BaseAddressControllerImpl<TravelAgencyAddress, TAAddressService>{

    private TravelAgencyService travelAgencyService;

//    @PreAuthorize("hasRole('USER')")
    @PostMapping
    @Override
    public ResponseEntity<AddressDto> create(@RequestBody @Valid AddressRequest request) {
        authorized(request.getEntityUuid());
        return super.create(request);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{uuid}")
    @Override
    public ResponseEntity<AddressDto> update(@PathVariable UUID uuid, @RequestBody @Valid AddressRequest request) {
        if(!service.existsByUuid(uuid))
            ResponseEntity.notFound().build();
        authorized(request.getEntityUuid());
        return super.update(uuid, request);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{uuid}")
    @Override
    public ResponseEntity<Void> deleteByUuid(@PathVariable UUID uuid) {
        if(!service.existsByUuid(uuid))
            ResponseEntity.notFound().build();
        TravelAgencyAddress dto = service.findById(uuid);
        if(dto == null)
            ResponseEntity.notFound().build();
        authorized(dto.getEntity().getUuid());
        return super.deleteByUuid(uuid);
    }

    private void authorized(UUID entityUuid){
        if(!travelAgencyService.existsByUuid(entityUuid))
            throw new CustomException(HttpStatus.NOT_FOUND, "no se encontró la agencia de viaje");
        if(!travelAgencyService.existsByUuidAndUserUsername(
                entityUuid, SecurityContextHolder.getContext().getAuthentication().getName()
        ))
            throw new CustomException(HttpStatus.UNAUTHORIZED, "sin autorización");
    }

}
