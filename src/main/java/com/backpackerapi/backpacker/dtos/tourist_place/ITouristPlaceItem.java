package com.backpackerapi.backpacker.dtos.tourist_place;

import java.time.Instant;
import java.util.UUID;

public interface ITouristPlaceItem {

    UUID getUuid();

    void setUuid(UUID uuid);

    String getName();

    void setName(String name);

    String getImageIcon();

    void setImageIcon(String imageIcon);

    String getResume();

    void setResume(String resume);

    String getCategories();

    void setCategories(String categories);

    Float getRating();

    void setRating(Float rating);

    Instant getCreatedAt();

    void setCreatedAt(Instant createdAt);
}
