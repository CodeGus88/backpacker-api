package com.backpackerapi.backpacker.models.address.address;

import com.backpackerapi.backpacker.models.TouristPlace;
import com.backpackerapi.backpacker.models.address.BaseAddress;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tp_addresses")
public class TPAddress extends BaseAddress<TouristPlace> {
    
    
    
}