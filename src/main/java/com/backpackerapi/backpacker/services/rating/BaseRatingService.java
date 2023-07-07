package com.backpackerapi.backpacker.services.rating;

import com.backpackerapi.backpacker.dtos.rating.IEntityRatingDto;
import com.backpackerapi.backpacker.dtos.rating.IRatingItem;
import com.backpackerapi.backpacker.dtos.rating.RatingDto;
import com.backpackerapi.backpacker.dtos.rating.RatingRequest;
import com.backpackerapi.backpacker.mappers.rating.BaseRatingMapper;
import com.backpackerapi.backpacker.models.BaseModel;
import com.backpackerapi.backpacker.repositories.rating.BaseRatingRepository;

import java.util.UUID;

public interface BaseRatingService<
        ENTITY,
        MAP extends BaseRatingMapper<ENTITY>,
        REPOSITORY extends BaseRatingRepository<ENTITY>
    > {

    RatingDto save(RatingRequest request);

    RatingDto update(UUID entityUuid, RatingRequest request);

    IEntityRatingDto findLastByEntityUuid(UUID entityUuid, long limit);

    IRatingItem findByEntityUuidAndUserUuid(UUID entityUuid, UUID userUuid);

    boolean deleteByUuid(UUID uuid);

    boolean existsByUuidAndUserUsername(UUID uuid, String username);

    boolean existsByEntityUuidAndUserUsername(UUID entityUuid, String username);
}
