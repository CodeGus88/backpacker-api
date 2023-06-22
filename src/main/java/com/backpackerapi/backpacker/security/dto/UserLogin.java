package com.backpackerapi.backpacker.security.dto;

import jakarta.validation.constraints.NotBlank;


public class UserLogin {

    @NotBlank(message = "nombre de usuario/email obligatorio")
    private String username;
    @NotBlank(message = "contraseña obligatoria")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
