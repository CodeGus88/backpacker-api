package com.backpackerapi.backpacker.repositories.file;

import com.backpackerapi.backpacker.dtos.files.FileRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public class FileNativeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public UUID insert(FileRequest request){
        UUID uuid = UUID.randomUUID();
        String query  = "INSERT INTO " + request.getTableName() + "(uuid, file, created_at, entity_uuid) VALUES(:uuid, :file, :created_at, :entity_uuid)";
        Query nativeQuery = entityManager.createNativeQuery(query);
        nativeQuery.setParameter("uuid", uuid);
        nativeQuery.setParameter("file", request.getFile());
        nativeQuery.setParameter("created_at", LocalDateTime.now());
        nativeQuery.setParameter("entity_uuid", request.getEntityUuid());
        nativeQuery.executeUpdate();
        return uuid;
    }

}
