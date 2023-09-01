package com.backpackerapi.backpacker.dtos.travel_agency;

import com.backpackerapi.backpacker.dtos.UserDto;
import com.backpackerapi.backpacker.dtos.address.AddressDto;
import lombok.Data;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Data
public class TravelAgencyDto {

    private UUID uuid;
    private String name;
    private String imageIcon;
    private boolean isPublic;
    private String description;
    private String keywords;
    private Instant createdAt;
    private Instant updatedAt;
    private Set<AddressDto> addresses;
    private UserDto user;

    public boolean getIsPublic(){
        return isPublic;
    }

    public void setIsPublic(boolean isPublic){
        this.isPublic = isPublic;
    }

}
