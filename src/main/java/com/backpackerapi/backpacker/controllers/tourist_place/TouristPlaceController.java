package com.backpackerapi.backpacker.controllers.tourist_place;

import com.backpackerapi.backpacker.controllers.BaseController;
import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceDto;
import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceRequest;
import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface TouristPlaceController extends BaseController<UUID, TouristPlaceItem, TouristPlaceRequest, TouristPlaceDto> {

    ResponseEntity<Page<TouristPlaceItem>> findAllPublicPageable(Pageable pageable);

    ResponseEntity<Page<TouristPlaceItem>> filterAllPublicPageable(Pageable pageable, String filter);

    ResponseEntity<String> updateImageIcon(UUID uuid, MultipartFile multipartFile);

}
