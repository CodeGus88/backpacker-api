package com.backpackerapi.backpacker.dtos.rating;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.UUID;

@Data
public class RatingRequest {

    @DecimalMin(value = "0.5", inclusive = true)
    @DecimalMax(value = "5.0", inclusive = true)
    private float punctuation;

    @Size(max = 255)
    private String comment;

    @NotNull
    private UUID entityUuid;

    private UUID userUuid;

}
