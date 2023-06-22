package com.backpackerapi.backpacker.dtos.rating;

import java.util.List;
import java.util.UUID;

public interface IEntityRatingDto {

    void setPunctuation(float punctuation);
    float getPunctuation();

    void setPopulation(long total);
    long getPopulation();

    void setEntityUuid(UUID uuid);
    UUID getEntityUuid();

    void setItems(List<IRatingItem> items);
    List<IRatingItem> getItems();

}
