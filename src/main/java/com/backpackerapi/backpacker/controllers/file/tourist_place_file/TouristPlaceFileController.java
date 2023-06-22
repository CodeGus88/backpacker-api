package com.backpackerapi.backpacker.controllers.file.tourist_place_file;

import com.backpackerapi.backpacker.controllers.file.BaseFileController;
import com.backpackerapi.backpacker.dtos.files.FileDto;
import com.backpackerapi.backpacker.models.file.TouristPlaceFile;
import com.backpackerapi.backpacker.services.file.tourist_placce_file.TouristPlaceFileService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


public interface TouristPlaceFileController extends BaseFileController
        <TouristPlaceFileService, TouristPlaceFile> {

    ResponseEntity<FileDto> uploadFile(UUID parentUuid, MultipartFile file);
    ResponseEntity<Resource> getResource(UUID parentModuleUuid, String fileName);

}
