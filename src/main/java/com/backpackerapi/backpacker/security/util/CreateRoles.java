package com.backpackerapi.backpacker.security.util;

import com.backpackerapi.backpacker.security.entity.Role;
import com.backpackerapi.backpacker.security.enums.Rolename;
import com.backpackerapi.backpacker.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Esta clase es temporal solo para crear los roles
 * Solo funciona con bases de datos en memoria porque escrito en disco tarda demasiado
 */
@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        if(true){ // con true crear los roles automáticamente
            Role adminRole = new Role(Rolename.ADMIN);
            Role userRole = new Role(Rolename.USER);
            roleService.save(adminRole);
            roleService.save(userRole);
        }
    }
}
