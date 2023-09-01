package com.backpackerapi.backpacker.dtos.tourist_place;

import com.backpackerapi.backpacker.dtos.address.AddressDto;
import com.backpackerapi.backpacker.dtos.category.CategoryDto;
import lombok.Data;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Data
public class TouristPlaceDto {

    private UUID uuid;
    private String name;
    private String imageIcon;
    private boolean isPublic;
    private String resume;
    private String keywords;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private Set<CategoryDto> categories;
//    private Set<FileDto> files;
    private Set<AddressDto> addresses;

    public boolean getIsPublic(){
        return isPublic;
    }

    public void setIsPublic(boolean isPublic){
        this.isPublic = isPublic;
    }

}
