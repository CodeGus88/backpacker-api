package com.backpackerapi.backpacker.controllers.file;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ImageIcon<DTO> {
    ResponseEntity<DTO> updateImageIcon(UUID touristPlaceUuid, MultipartFile file);
    ResponseEntity<DTO> removeImageIcon(UUID touristPlaceUuid, String imageIcon);
}
