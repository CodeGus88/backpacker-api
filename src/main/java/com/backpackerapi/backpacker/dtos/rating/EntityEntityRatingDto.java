package com.backpackerapi.backpacker.dtos.rating;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class EntityEntityRatingDto implements IEntityRatingDto {

    private float punctuation;

    private long population;

    private UUID entityUuid;

    private List<IRatingItem> items;

}
