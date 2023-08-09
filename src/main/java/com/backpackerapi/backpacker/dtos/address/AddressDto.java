package com.backpackerapi.backpacker.dtos.address;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.UUID;

@Data
public class AddressDto {

    private UUID uuid;

    private String title;

    private String address;

    private String continent;

    private String country;

    private String state;

    private String county;

    @Column(nullable = false)
    private double lat;

    @Column(nullable = false)
    private double lng;

}
