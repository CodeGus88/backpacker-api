package com.backpackerapi.backpacker.dtos.files;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class FileDto {

    private UUID uuid;
    private String file;
    private LocalDateTime createdAt;
    private UUID entityUuid;

}
