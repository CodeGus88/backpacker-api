package com.backpackerapi.backpacker.models.file;

import com.backpackerapi.backpacker.models.TouristPlace;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tourist_place_files")
public class TouristPlaceFile extends BaseFile<TouristPlace> {

}
