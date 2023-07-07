package com.backpackerapi.backpacker.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface BaseService <ID, ITEM, FORM, DTO> {

    DTO findById(ID uuid);

    Page<ITEM> findAllByIsPublic(Pageable pageable, String search, boolean isPublic);

    @Transactional
    DTO save(FORM request);

    @Transactional
    DTO update(ID uuid, FORM request);

    @Transactional
    boolean deleteById(ID uuid);

}
