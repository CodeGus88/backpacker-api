package com.backpackerapi.backpacker.controllers.file;

import com.backpackerapi.backpacker.dtos.files.FileDto;
import com.backpackerapi.backpacker.dtos.files.FileRequest;
import com.backpackerapi.backpacker.enums.EEntity;
import com.backpackerapi.backpacker.enums.EModule;
import com.backpackerapi.backpacker.models.file.BaseFile;
import com.backpackerapi.backpacker.services.file.BaseFileService;
import com.backpackerapi.backpacker.services.storange.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BaseFileControllerImpl
        <
        SERVICE extends BaseFileService<E>,
        E extends BaseFile
        >
        implements BaseFileController<SERVICE, E>{

    @Autowired
    private SERVICE service;

    @Autowired
    private StorageService storageService;

    @Override
    public ResponseEntity<FileDto> uploadFile(EModule eModule, EEntity eEntity, UUID entityUuid, MultipartFile file) {
        if(file.isEmpty())
            return ResponseEntity.badRequest().build();
        Map<String, String> fileData = storageService.uploadFile(file, eModule, entityUuid);
        FileRequest request = new FileRequest();
        request.setUuid(UUID.randomUUID());
        request.setFile(fileData.get("name"));
        request.setEntityUuid(entityUuid);
        request.setCreatedAt(LocalDateTime.now());
        request.setTableName(eEntity.name().toLowerCase());
        return ResponseEntity.ok().body(service.save(request));
    }

    @GetMapping("find-by-entity-uuid/{entityUuid}")
    @Override
    public ResponseEntity<List<FileDto>> findByEntityUuid(@PathVariable UUID entityUuid) {
        return ResponseEntity.ok(service.findByEntityUuid(entityUuid));
    }

    @Override
    public ResponseEntity<Resource> getResource(EModule eModule, UUID parentModuleUuid, String fileName) {
        Resource resource = storageService.loadResource(eModule, parentModuleUuid, fileName);
        if(resource == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(resource);
    }

    @GetMapping("{fileName:.+}")
    @Override
    public ResponseEntity<Resource> getFileFromDefaultDir(@PathVariable String fileName) {
        Resource resource = storageService.loadFromDefaultDirectory(fileName);
        if(resource == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(resource);
    }

    @Override
    public ResponseEntity<Void> deleteByUuid(EModule eModule, UUID fileUuid) {
//      cargar recurso
        FileDto file = service.findById(fileUuid);
//        Eliminar el archivo
        if(file == null)
            return ResponseEntity.notFound().build();
        boolean isDeleted = storageService.deleteFile(eModule, file.getEntityUuid(), file.getFile());
//        Eliminar de la base de datos
        if(isDeleted){
            service.deleteByUuid(fileUuid);
            if(!service.existByUuid(fileUuid))
                return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
