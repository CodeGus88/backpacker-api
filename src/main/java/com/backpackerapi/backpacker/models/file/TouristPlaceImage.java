package com.backpackerapi.backpacker.models.file;

import com.backpackerapi.backpacker.models.principal_models.TouristPlace;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tourist_place_image")
public class TouristPlaceImage extends BaseFile<TouristPlace> {



}
