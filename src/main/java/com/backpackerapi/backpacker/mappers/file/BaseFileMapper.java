package com.backpackerapi.backpacker.mappers.file;

import com.backpackerapi.backpacker.dtos.files.FileDto;
import com.backpackerapi.backpacker.dtos.files.FileRequest;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


public interface BaseFileMapper<ENTITY> {

    @Mappings(
            @Mapping(source = "entity.uuid", target = "entityUuid")
    )
    FileDto entityToDto(ENTITY entity);


    @Mappings(
            @Mapping(source = "entityUuid", target = "entity.uuid")
    )
    ENTITY requestToEntity(FileRequest request);

}
