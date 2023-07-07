package com.backpackerapi.backpacker.dtos.rating;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.UUID;

@Data
public class RatingRequest {

    @Range(min = 1, max = 5)
    private byte punctuation;

    @NotBlank
    private String comment;

    @NotNull
    private UUID entityUuid;

    private UUID userUuid;

}
