package com.backpackerapi.backpacker.dtos.rating;

import lombok.Data;
import java.util.UUID;

@Data
public class RatingRequest {

    private byte punctuation;

    private String comment;

    private UUID entityUuid;

    private UUID userUuid;

}
