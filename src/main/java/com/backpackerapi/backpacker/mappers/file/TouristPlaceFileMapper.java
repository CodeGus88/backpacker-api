package com.backpackerapi.backpacker.mappers.file;

import com.backpackerapi.backpacker.models.file.TouristPlaceFile;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TouristPlaceFileMapper extends BaseFileMapper<TouristPlaceFile>{

}
