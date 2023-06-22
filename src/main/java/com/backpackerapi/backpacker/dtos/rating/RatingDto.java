package com.backpackerapi.backpacker.dtos.rating;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RatingDto {

    private UUID uuid;
    private byte punctuation;
    private String comment;
    private UUID entityUuid;
    private UUID userUuid;
    private LocalDateTime createdAt;

}
