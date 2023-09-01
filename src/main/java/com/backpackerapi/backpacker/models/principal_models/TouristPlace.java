package com.backpackerapi.backpacker.models.principal_models;

import com.backpackerapi.backpacker.models.Category;
import com.backpackerapi.backpacker.models.address.TouristPlaceAddress;
import com.backpackerapi.backpacker.models.file.TouristPlaceImage;
import com.backpackerapi.backpacker.models.rating.TouristPlaceRating;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tourist_places")
public class TouristPlace extends BasicProperties<TouristPlaceRating, TouristPlaceImage, TouristPlaceAddress> {

//    @Column(name = "name", nullable = false, length = 35)
//    private String  name;
//
//    @Column(name = "image_icon")
//    private String imageIcon;
//
//    @Column(name = "keywords", nullable = false, length = 50)
//    private String keywords;
//
//    @Column(name = "description", nullable = false, length = 10000)
//    private String description;
//
//    @Column(name = "is_public")
//    private boolean isPublic;

    @Column(name = "resume", nullable = false, length = 500)
    private String resume;

    @OrderBy("name ASC")
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "tourist_places_categories",
        joinColumns = @JoinColumn(name = "tourist_place_uuid", referencedColumnName = "uuid"),
        inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private List<Category> categories;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "entity")
//    private Set<TouristPlaceRating> rating;
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "entity")
//    private List<TouristPlaceImages> gallery;
//
//    @OrderBy("title ASC")
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "entity")
//    private List<TouristPlaceAddress> addresses;

}
