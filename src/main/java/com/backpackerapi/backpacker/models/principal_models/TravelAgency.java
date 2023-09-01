package com.backpackerapi.backpacker.models.principal_models;

import com.backpackerapi.backpacker.models.address.TravelAgencyAddress;
import com.backpackerapi.backpacker.models.file.TravelAgencyImage;
import com.backpackerapi.backpacker.models.rating.TravelAgencyRating;
import com.backpackerapi.backpacker.security.entity.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "travel_agencies")
public class TravelAgency extends BasicProperties<TravelAgencyRating, TravelAgencyImage, TravelAgencyAddress> {

//    Propiedades propias de agencias de viaje
//    Promociones
//    Destinos

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user;

}
