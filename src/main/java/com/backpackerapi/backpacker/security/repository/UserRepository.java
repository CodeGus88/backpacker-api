package com.backpackerapi.backpacker.security.repository;

import com.backpackerapi.backpacker.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByTokenPassword(String tokenPassword);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
