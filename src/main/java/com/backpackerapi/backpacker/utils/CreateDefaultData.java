package com.backpackerapi.backpacker.utils;

import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceRequest;
import com.backpackerapi.backpacker.security.entity.Role;
import com.backpackerapi.backpacker.security.enums.ERole;
import com.backpackerapi.backpacker.security.service.RoleService;
import com.backpackerapi.backpacker.services.tourist_place.TouristPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Esta clase es temporal solo para crear los roles
 * Sólo funciona con bases de datos en memoria porque escrito en disco tarda demasiado
 */
@Component
public class CreateDefaultData implements CommandLineRunner {

    @Autowired
    private RoleService roleService;

    @Autowired
    private TouristPlaceService touristPlaceService;

    @Override
    public void run(String... args) throws Exception {

        boolean createTouristPlace = true;
        boolean createRoles = false;

        if(createRoles){
            Role adminRole = new Role(ERole.ROLE_ADMIN);
            Role userRole = new Role(ERole.ROLE_USER);
            if(!roleService.existsByName(ERole.ROLE_ADMIN))
                roleService.save(adminRole);
            if(!roleService.existsByName(ERole.ROLE_USER))
                roleService.save(userRole);
        }

        if(touristPlaceService.count() == 0 && createTouristPlace){
            for (int i = 1; i <= 100; i++){
                touristPlaceService.save(
                        new TouristPlaceRequest(
                                "name " + i,
                                true,
                                "Museo",
                                "Este es el resumen " + i,
                                "Estas son las palabras clave " + i,
                                "Esta es la descripción " + i
                        )
                );

            }
        }
    }
}
