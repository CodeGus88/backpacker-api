package com.backpackerapi.backpacker.controllers.file.tourist_place_file;

import com.backpackerapi.backpacker.controllers.file.BaseFileControllerImpl;
import com.backpackerapi.backpacker.dtos.files.FileDto;
import com.backpackerapi.backpacker.enums.EEntity;
import com.backpackerapi.backpacker.enums.EModule;
import com.backpackerapi.backpacker.models.file.TouristPlaceFile;
import com.backpackerapi.backpacker.services.file.tourist_placce_file.TouristPlaceFileService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

@RestController
@RequestMapping("api/media/tourist-place-files")
@CrossOrigin(origins = "*")
public class TouristPlaceFileControllerImpl
        extends BaseFileControllerImpl<TouristPlaceFileService, TouristPlaceFile>
        implements TouristPlaceFileController{

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<FileDto> uploadFile(
            @RequestParam("parentUuid") UUID parentUuid,
            @RequestParam("file") MultipartFile file
    ) {
        if(file.isEmpty() || parentUuid == null)
            return ResponseEntity.badRequest().build();
        return super.uploadFile(EModule.TOURIST_PLACES, EEntity.TOURIST_PLACE_FILES, parentUuid, file);
    }

    @GetMapping("{parentModuleUuid}/{fileName:.+}")
    public ResponseEntity<Resource> getResource(
            @PathVariable UUID parentModuleUuid,
            @PathVariable String fileName
    ) {
        return super.getResource(EModule.TOURIST_PLACES, parentModuleUuid, fileName);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{fileUuid}")
    public ResponseEntity<Void> deleteByUuid(@PathVariable UUID fileUuid) {
        return super.deleteByUuid(EModule.TOURIST_PLACES, fileUuid);
    }
}
