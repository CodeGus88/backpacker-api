package com.backpackerapi.backpacker.dtos.tourist_place;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TouristPlaceRequest /*implements Serializable*/ {

//    private static final long serialVersionUID = 74458L;

    @NotBlank
    @Size(min = 2, max = 35)
    private String name;

    @NotBlank
    @Size(min = 1, max = 15)
    private String category;

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

    public TouristPlaceRequest(
            String name,
            boolean isPublic,
            String category,
            String resume,
            String keywords,
            String description
    ) {
        this.name = name;
        this.isPublic = isPublic;
        this.category = category;
        this.resume = resume;
        this.keywords = keywords;
        this.description = description;
    }

    public TouristPlaceRequest(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
