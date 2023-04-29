package com.backpackerapi.backpacker.services.storange;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageServiceImpl implements StorageService {

    @Value("${files.tourist_places}")
    private String mediaLocation;

    @Autowired
    private HttpServletRequest request;

    private Path rootLocation;

    @Override
    @PostConstruct
    public void init() throws IOException {
        rootLocation = Paths.get(mediaLocation);
        Files.createDirectories(rootLocation);
        System.out.println(mediaLocation);
    }

    @Override
    public String uploadFile(MultipartFile file, String fileName) {
        try {
            if(file.isEmpty())
                throw new RuntimeException("File to store empty file");
//        file.getOriginalFilename();
            Path destinationFile = rootLocation
                    .resolve(Paths.get(fileName))
                    .normalize()
                    .toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
//            return fileName;
            String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
            return ServletUriComponentsBuilder
                    .fromHttpUrl(host)
                    .path("*/files/tourist_place/*")
                    .toUriString();
        }catch (IOException e){
            throw new RuntimeException("Failed to store file", e);
        }
    }

    @Override
    public Resource loadResource(String filename) {
        try{
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new RuntimeException("Could not read file " + filename);
            }
        }catch(MalformedURLException e){
            throw new RuntimeException("Could not read file: " + filename);
        }
    }
}
