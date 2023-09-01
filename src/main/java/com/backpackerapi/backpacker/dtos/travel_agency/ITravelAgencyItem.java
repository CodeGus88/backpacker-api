package com.backpackerapi.backpacker.dtos.travel_agency;

import java.time.Instant;
import java.util.UUID;

public interface ITravelAgencyItem {

    UUID getUuid();

    void setUuid(UUID uuid);

    String getName();

    void setName(String name);

    String getImageIcon();

    void setImageIcon(String imageIcon);

    Float getRating();

    void setRating(Float rating);

    Instant getCreatedAt();

    void setCreatedAt(Instant createdAt);

}
