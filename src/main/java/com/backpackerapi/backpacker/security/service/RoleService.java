package com.backpackerapi.backpacker.security.service;

import com.backpackerapi.backpacker.security.entity.Role;
import com.backpackerapi.backpacker.security.enums.ERole;
import com.backpackerapi.backpacker.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Optional<Role> getByName(ERole ERole){
        return roleRepository.findByName(ERole);
    }

    public void save(Role role){
        roleRepository.save(role);
    }

    public boolean existsByName(ERole name){
        return roleRepository.existsByName(name);
    }

}
