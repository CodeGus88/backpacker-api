package com.backpackerapi.backpacker.security.controller;

import com.backpackerapi.backpacker.dto.Message;
import com.backpackerapi.backpacker.security.dto.JwtDto;
import com.backpackerapi.backpacker.security.dto.LoginUser;
import com.backpackerapi.backpacker.security.dto.NewUser;
import com.backpackerapi.backpacker.security.entity.Role;
import com.backpackerapi.backpacker.security.entity.User;
import com.backpackerapi.backpacker.security.enums.Rolename;
import com.backpackerapi.backpacker.security.jwt.JwtProvider;
import com.backpackerapi.backpacker.security.service.RoleService;
import com.backpackerapi.backpacker.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private JwtProvider jwtProvider;


    @PostMapping("/create")
    public ResponseEntity<?> newUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        ArrayList<Message> messages = new ArrayList<>();
        if(bindingResult.hasErrors()){
            for (ObjectError objectError: bindingResult.getAllErrors()) {
                messages.add(new Message(
                        objectError.getCodes()[1],
                        objectError.getDefaultMessage())
                );
            }
        }
        if(userService.existsByUsername(newUser.getUsername())) {
            messages.add(new Message("username", "el nombre ya existe"));
        }
        if(userService.existsByEmail(newUser.getEmail())){
            messages.add(new Message("email", "el email ya existe"));
        }
        if(messages.size() == 0){
            User user = new User(
                    newUser.getName(),
                    newUser.getUsername(),
                    newUser.getEmail(),
                    passwordEncoder.encode(newUser.getPassword())
            );
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getByRolename(Rolename.USER).get());
            if(newUser.getRoles().contains("admin"))
                roles.add(roleService.getByRolename(Rolename.ADMIN).get());
            user.setRoles(roles);
            userService.save(user);
            messages.add(new Message("Created", "creado correctamente"));
            return new ResponseEntity(messages, HttpStatus.CREATED);
        }else{
            return new ResponseEntity(messages, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
        ArrayList<Message> messages = new ArrayList<>();
        if(bindingResult.hasErrors()){
            for (ObjectError objectError: bindingResult.getAllErrors()) {
                messages.add(new Message(
                        objectError.getCodes()[1],
                        objectError.getDefaultMessage())
                );
            }
            return new ResponseEntity(messages, HttpStatus.BAD_REQUEST);
        }else{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getUsername(),
                            loginUser.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            JwtDto jwtDto = new JwtDto(
                    jwt,
                    userDetails.getUsername(),
                    userDetails.getAuthorities()
            );
            return new ResponseEntity(jwtDto, HttpStatus.OK);
        }
    }

}
