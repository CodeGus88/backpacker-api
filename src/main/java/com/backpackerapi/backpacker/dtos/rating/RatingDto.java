package com.backpackerapi.backpacker.dtos.rating;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class RatingDto {

    private UUID uuid;
    private float punctuation;
    private String comment;
    private UUID entityUuid;
    private UUID userUuid;
    private Instant createdAt;

}
