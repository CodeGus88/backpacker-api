package com.backpackerapi.backpacker.controllers.tourist_place;

import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceDto;
import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceRequest;
import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceItem;
import com.backpackerapi.backpacker.services.storange.StorageServiceImpl;
import com.backpackerapi.backpacker.services.tourist_place.TouristPlaceService;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;
import java.util.UUID;

@RequestMapping("api/tourist_places")
@RestController
@CrossOrigin(origins = "*")
public class TouristPlaceControllerImpl implements TouristPlaceController {

    @Autowired
    private TouristPlaceService touristPlaceService;

    @Autowired
    StorageServiceImpl storageService;

    @GetMapping
    @Override
    public ResponseEntity<Set<TouristPlaceItem>> findAll() {
        return ResponseEntity.ok(touristPlaceService.findAll());
    }

//    @PreAuthorize("hasRole('USER')")
    @GetMapping("paged")
    @Override
    public ResponseEntity<Page<TouristPlaceItem>> findAllPublicPageable(Pageable pageable) {
        Page<TouristPlaceItem> page = touristPlaceService.findAllPublicPageable(pageable);
        return ResponseEntity.ok().body(page);
    }

//    @PreAuthorize("hasRole('USER')")
    @GetMapping("/filter/{filter}/paged")
    @Override
    public ResponseEntity<Page<TouristPlaceItem>> filterAllPublicPageable(Pageable pageable, @PathVariable("filter") String filter) {
        Page<TouristPlaceItem> page = touristPlaceService.searchPageable(pageable, filter);
        return ResponseEntity.ok().body(page);
    }



    @GetMapping("{uuid}")
    @Override
    public ResponseEntity<TouristPlaceDto> findById(@PathVariable UUID uuid) {
        TouristPlaceDto touristPlaceDto = touristPlaceService.findById(uuid);
        if(touristPlaceDto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(touristPlaceDto);
    }

    @PostMapping(value = "create")
    @Override
    public ResponseEntity<TouristPlaceDto> create(
            @RequestBody @Valid TouristPlaceRequest request
    ) {
        return ResponseEntity.ok().body(
                touristPlaceService.save(request)
        );
    }

    @PutMapping("update/{uuid}")
    @Override
    public ResponseEntity<TouristPlaceDto> update(@PathVariable UUID uuid,  @RequestBody @Valid TouristPlaceRequest request) {
        if(!touristPlaceService.existById(uuid))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(touristPlaceService.update(uuid, request));
    }

    @PutMapping("update/image-icon/{uuid}")
    @Override
    public ResponseEntity<String> updateImageIcon(@PathVariable UUID uuid, @RequestParam("file") MultipartFile multipartFile) {

        return null;
    }

    @DeleteMapping("{uuid}")
    @Override
    public ResponseEntity<Boolean> delete(@PathVariable UUID uuid) {
        return ResponseEntity.ok().body(touristPlaceService.deleteById(uuid));
    }
}
