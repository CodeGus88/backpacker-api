package com.backpackerapi.backpacker.dtos.rating;

import java.time.Instant;
import java.util.UUID;

public class RatingItem implements IRatingItem{

    private UUID uuid;

    private float punctuation;

    private String comment;

    private UUID userUuid;

    private String name;

    private String username;

    private String email;

    private Instant createdAt;

    @Override
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public UUID getUuid() {
        return this.uuid;
    }

    @Override
    public void setPunctuation(float punctuation) {
        this.punctuation = punctuation;
    }

    @Override
    public float getPunctuation() {
        return punctuation;
    }

    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }

    @Override
    public UUID getUserUuid() {
        return userUuid;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Instant getCreatedAt() {
        return createdAt;
    }
}
