package com.backpackerapi.backpacker.services.file;

import com.backpackerapi.backpacker.dtos.files.FileDto;
import com.backpackerapi.backpacker.dtos.files.FileRequest;
import com.backpackerapi.backpacker.mappers.file.BaseFileMapper;
import com.backpackerapi.backpacker.models.file.BaseFile;
import com.backpackerapi.backpacker.repositories.file.BaseFileRepository;
import com.backpackerapi.backpacker.repositories.file.FileNativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BaseFileServiceImpl
        <
            REPOSITORY extends BaseFileRepository<ENTITY>,
            ENTITY extends BaseFile,
            MAP extends BaseFileMapper<ENTITY>
        >
        implements BaseFileService<ENTITY> {

    @Autowired
    private REPOSITORY repository;

    @Autowired
    private FileNativeRepository nativeRepository;

    @Autowired
    private MAP mapper;

    @Transactional
    @Override
    public FileDto save(FileRequest request) {
        UUID uuid = nativeRepository.insert(request);
        return mapper.entityToDto(
                repository.findById(uuid).orElse(null)
        );
    }

    @Override
    public List<FileDto> findByEntityUuid(UUID entityUuid) {
        return repository.findByEntityOrderByCreatedAtAsc(entityUuid).stream().map(
                entity -> mapper.entityToDto(entity)
        ).collect(Collectors.toList());
    }

    @Override
    public FileDto findById(UUID uuid) {
        return mapper.entityToDto(repository.findById(uuid).get());
    }

    @Transactional
    @Override
    public boolean deleteByUuid(UUID uuid) {
        repository.deleteById(uuid);
        return true;
    }
}
