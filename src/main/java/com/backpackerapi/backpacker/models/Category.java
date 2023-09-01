package com.backpackerapi.backpacker.models;

import com.backpackerapi.backpacker.models.principal_models.TouristPlace;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 25)
    private String name;

    @Column(length = 500)
    private String description;

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TouristPlace> touristPlaces;

    public Category(String name, String description){
        this.name = name;
        this.description = description;
    }

}
