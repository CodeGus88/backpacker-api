package com.backpackerapi.backpacker.security.repository;
import com.backpackerapi.backpacker.security.entity.Role;
import com.backpackerapi.backpacker.security.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole ERole);
    boolean existsByName(ERole ERole);
}
