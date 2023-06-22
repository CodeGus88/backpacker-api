package com.backpackerapi.backpacker.security.controller;

import com.backpackerapi.backpacker.dtos.Message;
import com.backpackerapi.backpacker.security.dto.JwtDto;
import com.backpackerapi.backpacker.security.dto.UserRequest;
import com.backpackerapi.backpacker.security.dto.UserLogin;
import com.backpackerapi.backpacker.security.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Message> register(@Valid @RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.save(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody UserLogin loginUsuario){
        return ResponseEntity.ok(userService.login(loginUsuario));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
        return ResponseEntity.ok(userService.refresh(jwtDto));
    }
}
