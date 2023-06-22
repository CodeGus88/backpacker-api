package com.backpackerapi.backpacker.services.storange;

import com.backpackerapi.backpacker.enums.EModule;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public interface StorageService {

    Map<String, String> uploadFile(MultipartFile multipartFile, EModule EMODULE, UUID patentEntityUuid);

    Resource loadResource(EModule eModule, UUID patentEntityUuid, String fileName);

    Resource loadFromDefaultDirectory(String fileName);

    boolean deleteFile(EModule emodule, UUID patentEntityUuid, String fileName);

    boolean deleteParentDirectory(EModule eModule, UUID patentEntityUuid) throws IOException;

    boolean existFileByName(EModule eModule, UUID patentEntityUuid, String fileName);

    boolean existDirectoryByName(EModule eModule, UUID patentEntityUuid);

}
