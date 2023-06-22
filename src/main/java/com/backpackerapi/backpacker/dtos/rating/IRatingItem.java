package com.backpackerapi.backpacker.dtos.rating;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IRatingItem {

    void setUuid(UUID uuid);
    UUID getUuid();

    void setPunctuation(byte punctuation);
    byte getPunctuation();

    void setComment(String comment);
    String getComment();

    void setUserUuid(UUID userUuid);
    UUID getUserUuid();

    void setName(String username);
    String getName();

    void setUsername(String username);
    String getUsername();

    void setEmail(String email);
    String getEmail();

    void setCreatedAt(LocalDateTime date);
    LocalDateTime getCreatedAt();

}
