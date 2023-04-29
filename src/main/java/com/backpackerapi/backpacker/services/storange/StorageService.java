package com.backpackerapi.backpacker.services.storange;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {

    void init() throws IOException;

    String uploadFile(MultipartFile multipartFile, String fileName);

    Resource loadResource(String filename);

}
