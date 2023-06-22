package com.backpackerapi.backpacker.dtos.tourist_place;

import com.backpackerapi.backpacker.dtos.category.CategoryForRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class TouristPlaceRequest {

    @NotBlank
    @Size(min = 2, max = 35)
    private String name;

    private String imageIcon;

    @NotNull
    private boolean isPublic;

    @NotBlank
    @Size(min = 1, max = 500)
    private String resume;

    @NotBlank
    @Size(min = 1, max = 50)
    private String keywords;
    @NotBlank
    @Size(min = 1, max = 10000)
    private String description;

    private Set<CategoryForRequest> categories;

    public TouristPlaceRequest(
            String name,
            String imageIcon,
            boolean isPublic,
            Set<CategoryForRequest> categories,
            String resume,
            String keywords,
            String description
    ) {
        this.name = name;
        this.imageIcon = imageIcon;
        this.isPublic = isPublic;
        this.categories = categories;
        this.resume = resume;
        this.keywords = keywords;
        this.description = description;
    }

    public boolean getIsPublic(){
        return isPublic;
    }

    public void setIsPublic(boolean isPublic){
        this.isPublic = isPublic;
    }

}
