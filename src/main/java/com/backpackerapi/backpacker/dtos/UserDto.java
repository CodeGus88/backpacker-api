package com.backpackerapi.backpacker.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private UUID uuid;
    private String name;
    private String username;
    private String email;
}
