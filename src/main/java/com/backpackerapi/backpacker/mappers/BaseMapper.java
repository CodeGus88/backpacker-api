package com.backpackerapi.backpacker.mappers;

public interface BaseMapper<REQUEST, ITEM, DTO, ENTITY> {

    DTO entityToDto(ENTITY entity);

    ENTITY requestToEntity(REQUEST request);

    ITEM entityToItem (ENTITY entity);

}
