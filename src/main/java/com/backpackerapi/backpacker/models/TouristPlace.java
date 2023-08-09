package com.backpackerapi.backpacker.models;

import com.backpackerapi.backpacker.models.file.TouristPlaceFile;
import com.backpackerapi.backpacker.models.rating.TouristPlaceRating;
import com.backpackerapi.backpacker.models.address.address.TPAddress;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "tourist_places")
public class TouristPlace extends BaseModelX {

    @Column(name = "name", nullable = false, length = 35)
    private String  name;

    @Column(name = "image_icon")
    private String imageIcon;

    @Column(name = "is_public")
    private boolean isPublic;

    @Column(name = "resume", nullable = false, length = 500)
    private String resume;

    @Column(name = "keywords", nullable = false, length = 50)
    private String keywords;

    @Column(name = "description", nullable = false, length = 10000)
    private String description;

    @OrderBy("name ASC")
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "tourist_places_categories",
        joinColumns = @JoinColumn(name = "tourist_place_uuid", referencedColumnName = "uuid"),
        inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private List<Category> categories;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "entity")
    private Set<TouristPlaceRating> rating;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "entity")
    private List<TouristPlaceFile> files;

    @OrderBy("title ASC")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "entity")
    private List<TPAddress> addresses;

    public boolean getIsPublic(){
        return isPublic;
    }

    public void setIsPublic(boolean isPublic){
        this.isPublic = isPublic;
    }

}
