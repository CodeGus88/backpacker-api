package com.backpackerapi.backpacker.models.principal_models;

import com.backpackerapi.backpacker.models.BaseModelX;
import com.backpackerapi.backpacker.models.address.BaseAddress;
import com.backpackerapi.backpacker.models.file.BaseFile;
import com.backpackerapi.backpacker.models.rating.BaseRating;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@MappedSuperclass
public class BasicProperties<
        RATING extends BaseRating,
        IMAGE extends BaseFile,
        ADDRESS extends BaseAddress
        >
        extends BaseModelX {

    @Column(name = "name", nullable = false, length = 35)
    private String name;

    @Column(name = "image_icon")
    private String imageIcon;

    @Column(nullable = false, length = 50)
    private String keywords;

    @Column(nullable = false, length = 10000)
    private String description;

    @Column(name = "is_public")
    private boolean isPublic;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "entity")
    private Set<RATING> rating;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "entity")
    private List<IMAGE> gallery;

    @OrderBy("title ASC")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "entity")
    private List<ADDRESS> addresses;

    public boolean getIsPublic(){
        return isPublic;
    }

    public void setIsPublic(boolean isPublic){
        this.isPublic = isPublic;
    }

}
