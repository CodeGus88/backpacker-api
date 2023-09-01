package com.backpackerapi.backpacker.models.address;

import com.backpackerapi.backpacker.models.principal_models.TravelAgency;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "travel_agency_address")
public class TravelAgencyAddress extends BaseAddress<TravelAgency> {

}
