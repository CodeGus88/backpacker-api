package com.backpackerapi.backpacker.controllers.rating;

import com.backpackerapi.backpacker.dtos.rating.IEntityRatingDto;
import com.backpackerapi.backpacker.dtos.rating.IRatingItem;
import com.backpackerapi.backpacker.dtos.rating.RatingDto;
import com.backpackerapi.backpacker.dtos.rating.RatingRequest;
import com.backpackerapi.backpacker.security.entity.User;
import com.backpackerapi.backpacker.security.service.UserService;
import com.backpackerapi.backpacker.services.rating.BaseRatingService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

public class BaseRatingController<S extends BaseRatingService> {

    @Autowired
    private S service;

    @Autowired
    private UserService userService;

    @GetMapping("{entityUuid}/{limit}")
    public ResponseEntity<IEntityRatingDto> findLastByEntityUuid(@PathVariable UUID entityUuid, @PathVariable long limit){
        IEntityRatingDto ratingDto = service.findLastByEntityUuid(entityUuid, limit);
        return ResponseEntity.ok(ratingDto);
    }

    @GetMapping("this-auth-user/{entityUuid}")
    public ResponseEntity<IRatingItem> findByEntityUuidAndAuthUserUuid(@PathVariable UUID entityUuid){
        User user = userService.getAuthUser();
        if(user == null) // El usuario no existe
            return ResponseEntity.notFound().build();
        IRatingItem item = service.findByEntityUuidAndUserUuid(entityUuid, user.getUuid());
        if (item == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(item);
    }

//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @RolesAllowed({"ROLE_ADMIN", "USER"})
    @PostMapping
    public ResponseEntity<RatingDto> save(@RequestBody RatingRequest request){
        RatingDto ratingDto = service.save(request);
        return ResponseEntity.ok(ratingDto);
    }

    @PutMapping("/{entityUuid}")
    @PreAuthorize("hasRole('USER') AND #username == authentication.principal.username")
    public ResponseEntity<RatingDto> update(@PathVariable UUID entityUuid, @RequestBody RatingRequest request){
        RatingDto ratingDto = service.update(entityUuid, request);
        return ResponseEntity.ok(ratingDto);
    }

//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PreAuthorize("hasRole('ADMIN') OR (hasRole('USER') AND #username == authentication.principal.username)")
    @DeleteMapping("{uuid}")
    public ResponseEntity<?> deleteByUuid(@PathVariable UUID uuid){
//        Validad de que el registro le pertenezca al usuario
        service.deleteByUuid(uuid);
        return ResponseEntity.ok().build();
    }
}
