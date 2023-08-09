package com.backpackerapi.backpacker.services.rating;

import com.backpackerapi.backpacker.dtos.rating.*;
import com.backpackerapi.backpacker.enums.EEntity;
import com.backpackerapi.backpacker.mappers.rating.BaseRatingMapper;
import com.backpackerapi.backpacker.repositories.rating.BaseRatingRepository;

import java.util.UUID;

public interface BaseRatingService<
        ENTITY,
        MAP extends BaseRatingMapper<ENTITY>,
        REPOSITORY extends BaseRatingRepository<ENTITY>
    > {

    RatingItem save(RatingRequest request);

    RatingDto update(UUID entityUuid, RatingRequest request);

    IEntityRatingDto findLastByEntityUuid(UUID entityUuid, long limit);

    IRatingItem findByEntityUuidAndUserUuid(UUID entityUuid, UUID userUuid);

    SimplePunctuationDto punctuationByEntityUuid(EEntity eEntity, UUID entityUuid);

    boolean deleteByUuid(UUID uuid);

    boolean existsByUuidAndUserUsername(UUID uuid, String username);

    boolean existsByEntityUuidAndUserUsername(UUID entityUuid, String username);
}
