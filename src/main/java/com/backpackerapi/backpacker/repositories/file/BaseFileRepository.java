package com.backpackerapi.backpacker.repositories.file;

import com.backpackerapi.backpacker.models.file.BaseFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface BaseFileRepository<E extends BaseFile> extends JpaRepository<E, UUID> {

    List<E> findByEntityOrderByCreatedAtAsc(UUID entityUuid);

}
