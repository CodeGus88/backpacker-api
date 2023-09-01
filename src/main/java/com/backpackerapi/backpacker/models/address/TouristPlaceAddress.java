package com.backpackerapi.backpacker.models.address;

import com.backpackerapi.backpacker.models.principal_models.TouristPlace;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tourist_place_address")
public class TouristPlaceAddress extends BaseAddress<TouristPlace> {
    
    
    
}