package com.backpackerapi.backpacker.dtos.travel_agency;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelAgencyRequest {

    @NotBlank
    @Size(min = 2, max = 35)
    private String name;

    @Size(max = 255)
    private String imageIcon;

    @NotNull
    private boolean isPublic;

    @NotBlank
    @Size(min = 1, max = 50)
    private String keywords;

    @NotBlank
    @Size(min = 10, max = 10000)
    private String description;

    private UUID userUuid;

    public boolean getIsPublic(){
        return isPublic;
    }

    public void setIsPublic(boolean isPublic){
        this.isPublic = isPublic;
    }

}
