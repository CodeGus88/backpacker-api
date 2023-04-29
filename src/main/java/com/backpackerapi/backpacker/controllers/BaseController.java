package com.backpackerapi.backpacker.controllers;

import org.springframework.http.ResponseEntity;
import java.util.Set;

public interface BaseController <ID, ITEM, FORM, DTO>{

    ResponseEntity<Set<ITEM>> findAll();

    ResponseEntity<DTO> findById(ID id);

    ResponseEntity<DTO> create(FORM request);

    ResponseEntity<DTO> update(ID id, FORM request);

    ResponseEntity<Boolean> delete(ID id);

}
