package com.backpackerapi.backpacker.services;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface BaseService <ID, ITEM, FORM, DTO> {

    public DTO findById(ID uuid);

    public Set<ITEM> findAll();

    public Set<ITEM> findAllPageable(Pageable paginable);

    @Transactional
    public DTO save(FORM request);

    @Transactional
    public DTO update(ID uuid, FORM request);

    @Transactional
    public boolean deleteById(ID uuid);

}
