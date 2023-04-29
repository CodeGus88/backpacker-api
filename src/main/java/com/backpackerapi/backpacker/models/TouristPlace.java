package com.backpackerapi.backpacker.models;

import com.backpackerapi.backpacker.models.files.TouristPlaceFile;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tourist_places")
public class TouristPlace extends BaseModelX {

    @Column(name = "name", nullable = false, length = 35)
    private String  name;

    @Column(name = "image_icon")
    private String imageIcon;

    @Column(name = "is_public")
    private boolean isPublic;

    @Column(name = "category", nullable = false, length = 15)
    private String category;

    @Column(name = "resume", nullable = false, length = 500)
    private String resume;

    @Column(name = "keywords", nullable = false, length = 50)
    private String keywords;

    @Column(name = "description", nullable = false, length = 10000)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = ("touristPlace"))
    private Set<TouristPlaceFile> files;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
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

    public Set<TouristPlaceFile> getFiles() {
        return files;
    }

    public void setFiles(Set<TouristPlaceFile> files) {
        this.files = files;
    }
}
