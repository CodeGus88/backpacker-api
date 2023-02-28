package com.backpackerapi.backpacker.security.service;

import com.backpackerapi.backpacker.security.entity.Role;
import com.backpackerapi.backpacker.security.enums.Rolename;
import com.backpackerapi.backpacker.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Optional<Role> getByRolename(Rolename roleName){
        return roleRepository.findByRolename(roleName);
    }

    public void save(Role role){
        roleRepository.save(role);
    }
}
