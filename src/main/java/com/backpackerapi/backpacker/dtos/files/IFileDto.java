package com.backpackerapi.backpacker.dtos.files;

import java.time.Instant;
import java.util.UUID;

public interface IFileDto {
    UUID getUuid();
    void setUuid(UUID uuid);
    String getFile();
    void setFile(String file);
    Instant getCreatedAt();
    void setCreatedAt(Instant createdAt);
    UUID getEntityUuid();
    void setEntityUuid(UUID entityUuid);
}
