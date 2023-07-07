package com.backpackerapi.backpacker.models.rating;

import com.backpackerapi.backpacker.models.TouristPlace;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tourist_places_rating")
public class TouristPlaceRating extends BaseRating<TouristPlace> {

}
