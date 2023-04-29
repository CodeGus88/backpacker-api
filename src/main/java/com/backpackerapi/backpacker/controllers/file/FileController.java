package com.backpackerapi.backpacker.controllers.file;

import org.springframework.http.ResponseEntity;

import java.util.Set;
import java.util.UUID;

public interface FileController <REQ, DTO> {
    ResponseEntity<DTO> save(REQ req);
    ResponseEntity<Set<DTO>> findAllByTouristPlaceUUID(UUID uuid);
    ResponseEntity<DTO> findByUUID();
}
