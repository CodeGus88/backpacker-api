package com.backpackerapi.backpacker.controllers.rating;

import com.backpackerapi.backpacker.dtos.rating.*;
import com.backpackerapi.backpacker.enums.EEntity;
import com.backpackerapi.backpacker.exceptions.CustomException;
import com.backpackerapi.backpacker.security.entity.Role;
import com.backpackerapi.backpacker.security.entity.User;
import com.backpackerapi.backpacker.security.enums.ERole;
import com.backpackerapi.backpacker.security.service.RoleService;
import com.backpackerapi.backpacker.security.service.UserService;
import com.backpackerapi.backpacker.services.rating.BaseRatingService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

public class BaseRatingController<S extends BaseRatingService> {

    @Autowired
    private S service;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

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

    @PostMapping
    @RolesAllowed({"ROLE_ADMIN, ROLE_USER"})
    public ResponseEntity<RatingItem> create(@RequestBody @Valid RatingRequest request){
        User user = userService.getAuthUser();
        if(user == null)
            throw new CustomException(HttpStatus.NOT_FOUND, "No se puede identificar al usuario");
        request.setUserUuid(user.getUuid());
        RatingItem ratingItem = service.save(request);
        return ResponseEntity.ok(ratingItem);
    }

    @PutMapping("/{uuid}")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
    public ResponseEntity<RatingDto> update(@PathVariable UUID uuid, @RequestBody @Valid RatingRequest request){
        User user = userService.getAuthUser();
        if(user == null)
            throw new CustomException(HttpStatus.UNAUTHORIZED, "No se puede identificar al usuario");
        request.setUserUuid(user.getUuid());
        if(user.getUuid() != request.getUserUuid())
            throw new CustomException(HttpStatus.UNAUTHORIZED, "No autorizado");
        RatingDto ratingDto = service.update(uuid, request);
        return ResponseEntity.ok(ratingDto);
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
    @DeleteMapping("{uuid}")
    public ResponseEntity<Boolean> deleteByUuid(@PathVariable UUID uuid){
        User user = userService.getAuthUser();
        if(user == null)
            throw new CustomException(HttpStatus.UNAUTHORIZED, "No se puede identificar al usuario");
        if(!containsRole(user.getRoles(), ERole.ROLE_ADMIN)
                && !service.existsByUuidAndUserUsername(uuid, user.getUsername())
        )
                throw new CustomException(HttpStatus.UNAUTHORIZED, "No autorizado");
        return ResponseEntity.ok(service.deleteByUuid(uuid));
    }

    private boolean containsRole(Set<Role> roles, ERole eRole){
        return roles.stream().filter(i -> i.getName() == eRole).limit(1).count() > 0;
    }

    public SimplePunctuationDto punctuationByEntityUuid(EEntity eEntity, UUID entityUud){
        return service.punctuationByEntityUuid(eEntity, entityUud);
    }

}
