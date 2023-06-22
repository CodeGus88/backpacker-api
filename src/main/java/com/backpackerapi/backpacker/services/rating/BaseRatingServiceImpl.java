package com.backpackerapi.backpacker.services.rating;

import com.backpackerapi.backpacker.dtos.rating.*;
import com.backpackerapi.backpacker.exceptions.CustomException;
import com.backpackerapi.backpacker.mappers.rating.BaseRatingMapper;
import com.backpackerapi.backpacker.models.rating.BaseRating;
import com.backpackerapi.backpacker.repositories.rating.BaseRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BaseRatingServiceImpl<ENTITY extends BaseRating, MAPPER extends BaseRatingMapper<ENTITY>, REPOSITORY extends BaseRatingRepository<ENTITY>>
        implements BaseRatingService<ENTITY, MAPPER, REPOSITORY> {

    @Autowired
    private REPOSITORY repository;

    @Autowired
    private MAPPER mapper;

    @Transactional
    @Override
    public RatingDto save(RatingRequest request) {
        ENTITY entity = mapper.requestToEntity(request);
        entity.setCreatedAt(LocalDateTime.now());
        ENTITY savedEntity = repository.save(entity);
        return mapper.entityToRatingDto(savedEntity);
    }

    @Transactional
    @Override
    public RatingDto update(UUID uuid, RatingRequest request) {
        if(repository.existsById(uuid)){
            ENTITY old = repository.findById(uuid).get();
            ENTITY entity = mapper.requestToEntity(request);
            entity.setUuid(uuid);
            entity.setCreatedAt(old.getCreatedAt());
            entity.setUser(old.getUser());
            ENTITY savedEntity = repository.save(entity);
            return mapper.entityToRatingDto(savedEntity);
        } else {
             throw new CustomException(HttpStatus.NOT_FOUND, "No se encontró el recurso");
        }
    }

    @Override
    public IEntityRatingDto findLastByEntityUuid(UUID entityUuid, long limit) {
        IEntityRatingDto ratingDto = repository.calculatePunctuation(entityUuid);
        EntityEntityRatingDto rating = new EntityEntityRatingDto();
        if(ratingDto != null){
            rating.setPunctuation(ratingDto.getPunctuation());
            rating.setPopulation(ratingDto.getPopulation());
            rating.setEntityUuid(ratingDto.getEntityUuid());
            rating.setItems(repository.findLastByEntityUuid(entityUuid, limit));
        }else{
            rating.setPunctuation(0);
            rating.setPopulation(0);
            rating.setEntityUuid(entityUuid);
            rating.setItems(List.of());
        }
        return rating;
    }

    @Override
    public IRatingItem findByEntityUuidAndUserUuid(UUID entityUuid, UUID userUuid){
        return repository.findByEntityUuidAndUserUuid(entityUuid, userUuid).orElse(null);
    }

    @Transactional
    @Override
    public boolean deleteByUuid(UUID uuid) {
        if(!repository.existsById(uuid))
            throw new CustomException(HttpStatus.NOT_FOUND, "No se encontró el recurso");
        repository.deleteById(uuid);
        return true;
    }
}
