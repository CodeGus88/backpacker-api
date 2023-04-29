package com.backpackerapi.backpacker.security.repository;

import com.backpackerapi.backpacker.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String nombreUsuario);
    Optional<User> findByUsernameOrEmail(String nombreUsuario, String email);
    Optional<User> findByTokenPassword(String tokenPassword);
    boolean existsByUsername(String nombreUsuario);
    boolean existsByEmail(String email);
}
