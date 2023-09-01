package com.backpackerapi.backpacker.models.rating;

import com.backpackerapi.backpacker.models.principal_models.TravelAgency;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "travel_agency_rating")
public class TravelAgencyRating extends BaseRating<TravelAgency>{

}
