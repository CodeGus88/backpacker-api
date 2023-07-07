package com.backpackerapi.backpacker.mappers.rating;


import com.backpackerapi.backpacker.dtos.rating.RatingDto;
import com.backpackerapi.backpacker.dtos.rating.RatingRequest;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

public interface BaseRatingMapper<ENTITY> {

    @Mappings({
            @Mapping(source = "entityUuid", target = "entity.uuid"),
            @Mapping(source = "userUuid", target = "user.uuid")
    })
    ENTITY requestToEntity(RatingRequest request);

    @Mappings({
            @Mapping(source = "entity.uuid", target = "entityUuid"),
            @Mapping(source = "user.uuid", target = "userUuid")
    })
    RatingDto entityToRatingDto(ENTITY entity);

}
