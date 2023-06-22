package com.backpackerapi.backpacker.dtos.files;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IFileDto {
    UUID getUuid();
    void setUuid(UUID uuid);
    String getFile();
    void setFile(String file);
    LocalDateTime getCreatedAt();
    void setCreatedAt(LocalDateTime createdAt);
    UUID getEntityUuid();
    void setEntityUuid(UUID entityUuid);
}
