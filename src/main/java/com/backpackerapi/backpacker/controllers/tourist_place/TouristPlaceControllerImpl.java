package com.backpackerapi.backpacker.controllers.tourist_place;

import com.backpackerapi.backpacker.controllers.file.ImageIcon;
import com.backpackerapi.backpacker.dtos.tourist_place.ITouristPlaceItem;
import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceDto;
import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceRequest;
import com.backpackerapi.backpacker.enums.EModule;
import com.backpackerapi.backpacker.mappers.TouristPlaceMapper;
import com.backpackerapi.backpacker.services.storange.StorageServiceImpl;
import com.backpackerapi.backpacker.services.tourist_place.TouristPlaceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;
import java.util.UUID;

@RequestMapping("api/tourist-places")
@RestController
@CrossOrigin(origins = "*")
public class TouristPlaceControllerImpl implements TouristPlaceController, ImageIcon<TouristPlaceDto> {

    @Autowired
    private TouristPlaceService touristPlaceService;

    @Autowired
    StorageServiceImpl storageService;

    @Autowired
    private TouristPlaceMapper mapper;

    @GetMapping
    @Override
    public ResponseEntity<Page<ITouristPlaceItem>> findAll(Pageable pageable, String filter) {
        Page<ITouristPlaceItem> list = touristPlaceService.findAllByIsPublic(pageable, filter, true);
        return ResponseEntity.ok(list);
    }

    @GetMapping("privates")
    @Override
    public ResponseEntity<Page<ITouristPlaceItem>> findAllPrivates(Pageable pageable, String filter) {
        Page<ITouristPlaceItem> list = touristPlaceService.findAllByIsPublic(pageable, filter, false);
        return ResponseEntity.ok(list);
    }

    @GetMapping("{uuid}")
    @Override
    public ResponseEntity<TouristPlaceDto> findById(@PathVariable UUID uuid) {
        TouristPlaceDto touristPlaceDto = touristPlaceService.findById(uuid);
        if(touristPlaceDto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(touristPlaceDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("create")
    @Override
    public ResponseEntity<TouristPlaceDto> create(
            @RequestBody @Valid TouristPlaceRequest request
    ) {
        return ResponseEntity.ok().body(touristPlaceService.save(request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{uuid}")
    @Override
    public ResponseEntity<TouristPlaceDto> update(
            @PathVariable UUID uuid,
            @RequestBody @Valid TouristPlaceRequest request
    ) {
        if(!touristPlaceService.existById(uuid))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(touristPlaceService.update(uuid, request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{uuid}")
    @Override
    public ResponseEntity<Void> deleteByUuid(@PathVariable UUID uuid) {
        if(storageService.existDirectoryByName(EModule.TOURIST_PLACES, uuid))
            storageService.deleteParentDirectory(EModule.TOURIST_PLACES, uuid);
        if(!storageService.existDirectoryByName(EModule.TOURIST_PLACES, uuid))
            if(touristPlaceService.deleteById(uuid))
                return ResponseEntity.noContent().build();
            else return ResponseEntity.notFound().build();
        return ResponseEntity.internalServerError().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("update/image-icon")
    @Override
    public ResponseEntity<TouristPlaceDto> updateImageIcon(
            @RequestParam("uuid") UUID touristPlaceUuid,
            @RequestParam("file") MultipartFile file
    ){
        //        Verificar si existe el lugar turístico
        if(touristPlaceService.existById(touristPlaceUuid)){
            TouristPlaceDto touristPlaceDto = touristPlaceService.findById(touristPlaceUuid);
            TouristPlaceRequest request = mapper.dtoToRequest(touristPlaceDto);
        //        Eliminar imagen existente
            if(touristPlaceDto.getImageIcon() != null && touristPlaceDto.getImageIcon() != "")
                storageService.deleteFile(EModule.TOURIST_PLACES, touristPlaceUuid, touristPlaceDto.getImageIcon());
        //        Guardar el archivo
            Map<String, String> map = storageService.uploadFile(
                    file,
                    EModule.TOURIST_PLACES,
                    touristPlaceUuid
            );
        //      Actualizar en la base de datos
            request.setImageIcon(map.get("name"));
            return ResponseEntity.ok().body(touristPlaceService.update(touristPlaceUuid, request));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("remove/image-icon")
    @Override
    public ResponseEntity<TouristPlaceDto> removeImageIcon(@RequestParam UUID uuid, @RequestParam String imageIcon) {
        if(!touristPlaceService.existById(uuid))
            return ResponseEntity.notFound().build(); //no existe el registro
        if(imageIcon == "" || imageIcon == null) return ResponseEntity.badRequest().build(); // no existe el archivo recibido
        if(storageService.existFileByName(EModule.TOURIST_PLACES, uuid, imageIcon))
            if(!storageService.deleteFile(EModule.TOURIST_PLACES, uuid, imageIcon))
                return ResponseEntity.notFound().build(); // no existe el archivo solicitado
        TouristPlaceRequest request = mapper.dtoToRequest(touristPlaceService.findById(uuid));
        request.setImageIcon(null);
        return ResponseEntity.ok(touristPlaceService.update(uuid, request));
    }

}
