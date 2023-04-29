package com.backpackerapi.backpacker.models.files;

import com.backpackerapi.backpacker.models.TouristPlace;
import jakarta.persistence.*;


@Entity
@Table(name = "tourist_place_files")
public class TouristPlaceFile extends BaseFile {

    @JoinColumn(name = "tourist_place_uuid", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private TouristPlace touristPlace;

    public TouristPlace getTouristPlace() {
        return touristPlace;
    }

    public void setTouristPlace(TouristPlace touristPlace) {
        this.touristPlace = touristPlace;
    }

}
