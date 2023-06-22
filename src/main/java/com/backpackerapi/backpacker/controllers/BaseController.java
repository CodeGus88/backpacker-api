package com.backpackerapi.backpacker.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface BaseController <ID, ITEM, FORM, DTO>{

    ResponseEntity<Page<ITEM>> findAll(Pageable pageable, String filter);

    ResponseEntity<DTO> findById(ID id);

    ResponseEntity<DTO> create(FORM request);

    ResponseEntity<DTO> update(ID id, FORM request);

    ResponseEntity<Boolean> deleteByUuid(ID id);

}
