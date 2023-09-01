package com.backpackerapi.backpacker.dtos.rating;

import java.time.Instant;
import java.util.UUID;

public interface IRatingItem {

    void setUuid(UUID uuid);
    UUID getUuid();

    void setPunctuation(float punctuation);
    float getPunctuation();

    void setComment(String comment);
    String getComment();

    void setUserUuid(UUID userUuid);
    UUID getUserUuid();

    void setName(String name);
    String getName();

    void setUsername(String username);
    String getUsername();

    void setEmail(String email);
    String getEmail();

    void setCreatedAt(Instant createdAt);
    Instant getCreatedAt();

}
