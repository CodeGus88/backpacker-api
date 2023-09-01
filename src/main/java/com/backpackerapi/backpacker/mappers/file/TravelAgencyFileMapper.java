package com.backpackerapi.backpacker.mappers.file;

import com.backpackerapi.backpacker.models.file.TravelAgencyImage;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TravelAgencyFileMapper extends BaseFileMapper<TravelAgencyImage>{

}
