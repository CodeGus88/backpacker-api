package com.backpackerapi.backpacker.services.tourist_place;

import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceDto;
import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceRequest;
import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceItem;
import com.backpackerapi.backpacker.services.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TouristPlaceService extends BaseService<UUID, TouristPlaceItem, TouristPlaceRequest, TouristPlaceDto> {

    Page<TouristPlaceItem>  findAllPublicPageable(Pageable pageable);

    Page<TouristPlaceItem> searchPageable(Pageable pageable, String search);

    long count();

    boolean existById(UUID uuid);

}
