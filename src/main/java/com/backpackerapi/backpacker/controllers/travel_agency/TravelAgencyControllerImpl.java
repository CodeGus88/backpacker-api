package com.backpackerapi.backpacker.controllers.travel_agency;

import com.backpackerapi.backpacker.dtos.travel_agency.ITravelAgencyItem;
import com.backpackerapi.backpacker.dtos.travel_agency.TravelAgencyDto;
import com.backpackerapi.backpacker.dtos.travel_agency.TravelAgencyRequest;
import com.backpackerapi.backpacker.exceptions.CustomException;
import com.backpackerapi.backpacker.security.entity.User;
import com.backpackerapi.backpacker.security.service.UserService;
import com.backpackerapi.backpacker.services.travel_agency.TravelAgencyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("api/travel-agencies")
@CrossOrigin(origins = "*")
public class TravelAgencyControllerImpl implements TravelAgencyController {

    private TravelAgencyService service;
    private UserService userService;

    @GetMapping
    @Override
    public ResponseEntity<Page<ITravelAgencyItem>> findAll(Pageable pageable, String filter) {
        return ResponseEntity.ok(service.findAllByIsPublic(pageable, filter, true));
    }

    @GetMapping("privates")
    @Override
    public ResponseEntity<Page<ITravelAgencyItem>> findAllPrivates(Pageable pageable, String filter) {
        return null;
    }

    @GetMapping("{uuid}")
    @Override
    public ResponseEntity<TravelAgencyDto> findById(UUID uuid) {
        TravelAgencyDto dto = service.findById(uuid);
        if(dto == null)
            throw new CustomException(HttpStatus.NOT_FOUND, "no se encuentra el recurso");
        return ResponseEntity.ok(dto);
    }

    @PostMapping("create")
    @Override
    public ResponseEntity<TravelAgencyDto> create(@RequestBody TravelAgencyRequest request) {
        User user = userService.getAuthUser();
        request.setUserUuid(user.getUuid());
        return ResponseEntity.ok(service.save(request));
    }

    @PutMapping("/update/{uuid}")
    @Override
    public ResponseEntity<TravelAgencyDto> update(UUID uuid, TravelAgencyRequest request) {
        authorized(uuid);
        return ResponseEntity.ok(service.update(uuid, request));
    }

    @DeleteMapping("{uuid}")
    @Override
    public ResponseEntity<Void> deleteByUuid(UUID uuid) {
        authorized(uuid);
        if(service.deleteById(uuid))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();
    }

    private void authorized(UUID uuid){
        TravelAgencyDto dto = service.findById(uuid);
        if(dto == null)
            throw new CustomException(HttpStatus.NOT_FOUND, "no se encuentra el recurso");
        if(dto.getUser().getUsername() != SecurityContextHolder.getContext().getAuthentication().getName())
            throw new CustomException(HttpStatus.UNAUTHORIZED, "permiso denegado");
    }

}
