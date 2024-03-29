package com.backpackerapi.backpacker.dtos.address;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class AddressRequest {

    @NotBlank
    @Size(max = 30)
    private String title;

    @NotBlank
    @Size(max = 100)
    private String address;

    private String continent;

    private String country;

    private String state;

    private String county;

    @Column(nullable = false)
    private double lat;

    @Column(nullable = false)
    private double lng;

    @NotNull
    private UUID entityUuid;

}
