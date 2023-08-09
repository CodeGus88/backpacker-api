package com.backpackerapi.backpacker.controllers.file;

import com.backpackerapi.backpacker.dtos.files.FileDto;
import com.backpackerapi.backpacker.enums.EEntity;
import com.backpackerapi.backpacker.enums.EModule;
import com.backpackerapi.backpacker.models.file.BaseFile;
import com.backpackerapi.backpacker.services.file.BaseFileService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface BaseFileController<
        SERVICE extends BaseFileService<E>,
        E extends BaseFile
        > {

    ResponseEntity<FileDto> uploadFile(
            EModule eModule,
            EEntity eEntity,
            UUID parentUuid,
            MultipartFile file
    );

    ResponseEntity<List<FileDto>> findByEntityUuid(UUID entityUuid);

    ResponseEntity<Resource> getResource(
            EModule eModule,
            UUID parentModuleUuid,
            String fileName
    );

    ResponseEntity<Resource> getFileFromDefaultDir(String fileName);

    ResponseEntity<Void> deleteByUuid(EModule eModule, UUID fileUuid);
}
