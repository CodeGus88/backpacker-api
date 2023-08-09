package com.backpackerapi.backpacker.dtos.rating;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RatingItem implements IRatingItem{

    private UUID uuid;

    private float punctuation;

    private String comment;

    private UUID userUuid;

    private String name;

    private String username;

    private String email;

    private LocalDateTime createdAt;

}
