package com.backpackerapi.backpacker.dtos.files;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class FileRequest {
    private UUID uuid;
    private String file;
    private UUID entityUuid;
    private Instant createdAt;
    private String tableName;
}
