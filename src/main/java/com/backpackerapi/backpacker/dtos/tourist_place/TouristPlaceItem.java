package com.backpackerapi.backpacker.dtos.tourist_place;

import java.time.Instant;
import java.util.UUID;


public class TouristPlaceItem implements ITouristPlaceItem {

    private UUID uuid;
    private String name;
    private String imageIcon;
    private String resume;
    private String categories;
    private Float rating;
    private Instant createdAt;

    public TouristPlaceItem(UUID uuid, String name, String imageIcon, String resume, String categories, Instant createdAt) {
        this.uuid = uuid;
        this.name = name;
        this.imageIcon = imageIcon;
        this.resume = resume;
        this.categories = categories;
        this.createdAt = createdAt;
    }

    public TouristPlaceItem(){}

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getImageIcon() {
        return imageIcon;
    }

    @Override
    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
    }

    @Override
    public String getResume() {
        return resume;
    }

    @Override
    public void setResume(String resume) {
        this.resume = resume;
    }

    @Override
    public String getCategories() {
        return categories;
    }

    @Override
    public void setCategories(String categories) {
        this.categories = categories;
    }

    @Override
    public Float getRating() {
        return rating;
    }

    @Override
    public void setRating(Float rating) {
        this.rating = rating;
    }

    @Override
    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

}
