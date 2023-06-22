package com.backpackerapi.backpacker.services.file;

import com.backpackerapi.backpacker.dtos.files.FileDto;
import com.backpackerapi.backpacker.dtos.files.FileRequest;
import com.backpackerapi.backpacker.models.file.BaseFile;

import java.util.List;
import java.util.UUID;

public interface BaseFileService<E extends BaseFile> {

    FileDto save(FileRequest request);

    List<FileDto> findByEntityUuid(UUID entityUuid);

    FileDto findById(UUID uuid);

    boolean deleteByUuid(UUID uuid);

}
