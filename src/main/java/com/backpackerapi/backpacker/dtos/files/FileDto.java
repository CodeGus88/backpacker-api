package com.backpackerapi.backpacker.dtos.files;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class FileDto {

    private UUID uuid;
    private String file;
    private Instant createdAt;
    private UUID entityUuid;

}
