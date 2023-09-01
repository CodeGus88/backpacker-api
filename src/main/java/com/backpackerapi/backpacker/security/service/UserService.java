package com.backpackerapi.backpacker.security.service;

import com.backpackerapi.backpacker.dtos.Message;
import com.backpackerapi.backpacker.exceptions.CustomException;
import com.backpackerapi.backpacker.security.dto.JwtDto;
import com.backpackerapi.backpacker.security.dto.UserLogin;
import com.backpackerapi.backpacker.security.dto.UserRequest;
import com.backpackerapi.backpacker.security.entity.Role;
import com.backpackerapi.backpacker.security.entity.User;
import com.backpackerapi.backpacker.security.enums.ERole;
import com.backpackerapi.backpacker.security.jwt.JwtProvider;
import com.backpackerapi.backpacker.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.ParseException;
import java.util.*;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtProvider jwtProvider;

    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<User> getByUsernameOrEmail(String usernameOrEmail){
        return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
    }

    public Optional<User> getByTokenPassword(String tokenPassword){
        return userRepository.findByTokenPassword(tokenPassword);
    }

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public JwtDto login(UserLogin userLogin){
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        return new JwtDto(jwt);
    }

    public JwtDto refresh(JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        return new JwtDto(token);
    }

    public Message save(UserRequest userRequest){
        if(userRepository.existsByUsername(userRequest.getUsername()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "ese nombre de usuario ya existe");
        if(userRepository.existsByEmail(userRequest.getEmail()))
            throw new CustomException(HttpStatus.BAD_REQUEST, "ese email de usuario ya existe");
        User user =
                new User(userRequest.getName(), userRequest.getUsername(), userRequest.getEmail(),
                        passwordEncoder.encode(userRequest.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRolNombre(ERole.ROLE_USER).get());
        if(userRequest.getRoles().contains("admin"))
            roles.add(roleService.getByRolNombre(ERole.ROLE_ADMIN).get());
        user.setRoles(roles);
        userRepository.save(user);
        return new Message(user.getUsername() + " ha sido creado");
    }

    /**
     * Obtiene los detalles del usuario autentificado
     * @return userDetails
     */
    public UserDetails getAuthUserDetails(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if(principal instanceof UserDetails)
            userDetails = (UserDetails) principal;
        if(userDetails != null)
            return userDetails;
        else
            throw new CustomException(HttpStatus.NOT_FOUND, "sin autentificar");
    }

    /**
     * Obtiene al usuario autentificado
     * @return user
     */
    public User getAuthUser(){
        UserDetails userDetails = getAuthUserDetails();
        User user = null;
        if(userDetails != null)
            user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() ->{
                throw new CustomException(HttpStatus.NOT_FOUND, "el usuario no existe");
            });
        return user;
    }
}
