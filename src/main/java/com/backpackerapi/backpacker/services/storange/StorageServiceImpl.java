package com.backpackerapi.backpacker.services.storange;

import com.backpackerapi.backpacker.enums.EModule;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {

    @Value("${storage}")
    private String mediaDirectory;

    @Autowired
    private HttpServletRequest request;

    private Path rootLocation;

    private Logger logger = LoggerFactory.getLogger(Exception.class);

    @Override
    public Map<String, String> uploadFile(MultipartFile file, EModule EMODULE, UUID patentEntityUuid) {
        try {
            String mediaDirectory = this.mediaDirectory + "//" + EMODULE.name().toLowerCase() + "//" + patentEntityUuid.toString();
            rootLocation = Paths.get(mediaDirectory);
            Files.createDirectories(rootLocation);
            if(file.isEmpty())
                throw new RuntimeException("File to store empty file");
            String ext = getExtension(file.getOriginalFilename());
            String fileName = UUID.randomUUID().toString();
            fileName += ext;
            Path destinationFile = rootLocation
                    .resolve(Paths.get(fileName))
                    .normalize()
                    .toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
            String url = ServletUriComponentsBuilder
                    .fromHttpUrl(host)
                    .path("/api/media/" + EMODULE.name().toLowerCase() + "/"+ patentEntityUuid + "/")
                    .path(fileName)
                    .toUriString();
            Map<String, String> map = new HashMap<>();
            map.put("name", fileName);
            map.put("url", url);
            return map;
        }catch (IOException e){
            logger.error(e.getMessage());
            throw new RuntimeException("Failed to store file", e);
        }
    }

    public static String getExtension(String fileName){
        String ext = "";
        for (int i = fileName.length()-1; i >= 0; i--) {
            ext += fileName.charAt(i);
            if(fileName.charAt(i)=='.'){
                ext = new StringBuilder(ext).reverse().toString();
                break;
            }
        }
        return ext.contains(".")?ext:"";
    }

    @Override
    public Resource loadResource(EModule eModule, UUID patentEntityUuid, String fileName) {
        try{
            String mediaDirectory = this.mediaDirectory + "//" + eModule.name().toLowerCase() + "//" + patentEntityUuid.toString();
            rootLocation = Paths.get(mediaDirectory);
            Path file = rootLocation.resolve(fileName);
            Resource resource = new UrlResource(file.toUri()); // si es nulo, hacer otro intento con otra clase
            resource = resource == null? new FileSystemResource(file.toUri().toString()):resource;
            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                return null;
            }
        }catch(Exception e){
            throw new RuntimeException("Could not read file: " + fileName);
        }
    }

    @Override
    public Resource loadFromDefaultDirectory(String fileName){
        try {
            rootLocation = Paths.get(mediaDirectory);
            Path file = rootLocation.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            resource = resource == null? new FileSystemResource(file.toUri().toString()):resource;
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else{
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not read file: " + fileName);
        }
    }

    @Override
    public boolean deleteFile(EModule emodule, UUID patentEntityUuid, String fileName){
        String filePath = this.mediaDirectory + "//" + emodule.name().toLowerCase() + "//" + patentEntityUuid.toString() + "//" + fileName;
        File file = new File(filePath);
        if(file.exists())
            return file.delete();
        else
            return true;
    }

    /**
     * Eliminar el directorio padre
     * @param eModule
     * @param patentEntityUuid
     * @return success
     */
    @Override
    public boolean deleteParentDirectory(EModule eModule, UUID patentEntityUuid) {
        try{
            String filePath = this.mediaDirectory + "//" + eModule.name().toLowerCase() + "//" + patentEntityUuid.toString();
            if(new File(filePath).exists()){
                File directory = new File(filePath);
                FileUtils.deleteDirectory(directory);
                return !new File(filePath).exists();
            } else {
                throw new RuntimeException("Not found exception");
            }
        }catch (IOException e){
            throw new RuntimeException("delete parent directory error: " + e.getMessage());
        }
    }

    @Override
    public boolean existFileByName(EModule eModule, UUID patentEntityUuid, String fileName){
        String filePath = this.mediaDirectory + "//" + eModule.name().toLowerCase() + "//" + patentEntityUuid.toString();
        return new File(filePath).exists();
    }

    @Override
    public boolean existDirectoryByName(EModule eModule, UUID patentEntityUuid){
        String directory = this.mediaDirectory + "//" + eModule.name().toLowerCase() + "//" + patentEntityUuid.toString();
        return new File(directory).exists();
    }
}
