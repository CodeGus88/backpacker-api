package com.backpackerapi.backpacker.security.service;

import com.backpackerapi.backpacker.security.entity.User;
import com.backpackerapi.backpacker.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional // Mantiene la coherencia de los datos, si hay error hace rollback y vuelve al estado anterior
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }

}
