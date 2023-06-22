package com.backpackerapi.backpacker.services.file.tourist_placce_file;

import com.backpackerapi.backpacker.mappers.file.TouristPlaceFileMapper;
import com.backpackerapi.backpacker.models.file.TouristPlaceFile;
import com.backpackerapi.backpacker.repositories.file.TouristPLaceFileRepository;
import com.backpackerapi.backpacker.services.file.BaseFileServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TouristPlaceFileServiceImpl extends BaseFileServiceImpl
        <
        TouristPLaceFileRepository,
        TouristPlaceFile,
        TouristPlaceFileMapper
        > implements TouristPlaceFileService{

}
