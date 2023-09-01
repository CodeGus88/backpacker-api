package com.backpackerapi.backpacker.repositories.address;

import com.backpackerapi.backpacker.models.address.TravelAgencyAddress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;


@Repository
public interface TravelAgencyAddressRepository extends BaseAddressRepository<TravelAgencyAddress>{


}
