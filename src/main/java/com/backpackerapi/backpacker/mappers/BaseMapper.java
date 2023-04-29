package com.backpackerapi.backpacker.mappers;

public interface BaseMapper<FORM, ITEM, DTO, ENTITY> {

    DTO entityToDto(ENTITY entity);

    ENTITY requestToEntity(FORM request);

    ITEM entityToItem (ENTITY entity);

}
