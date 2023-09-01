package com.backpackerapi.backpacker.dtos.travel_agency;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class TravelAgencyItem implements ITravelAgencyItem {

    private UUID uuid;
    private String name;
    private String imageIcon;
    private Float rating;
    private Instant createdAt;

}
