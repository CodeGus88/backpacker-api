package com.backpackerapi.backpacker.dtos.files;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class FileRequest {
    private UUID uuid;
    private String file;
    private UUID entityUuid;
    private LocalDateTime createdAt;
    private String tableName;
}
