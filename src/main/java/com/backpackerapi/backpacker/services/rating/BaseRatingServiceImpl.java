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
        if(
                repository.existsByEntityUuidAndUserUuid(
                        request.getEntityUuid(),
                        request.getUserUuid()
                )
        )
            throw new CustomException(HttpStatus.ALREADY_REPORTED, "El usuario ya tiene registrado puntuación para este recurso");
        ENTITY entity = mapper.requestToEntity(request);
        entity.setUuid(UUID.randomUUID());
        entity.setCreatedAt(LocalDateTime.now());
        ENTITY savedEntity = repository.save(entity);
        return mapper.entityToRatingDto(savedEntity);
    }

    @Transactional
    @Override
    public RatingDto update(UUID uuid, RatingRequest request) {
        if(repository.existsById(uuid)){
            ENTITY oldEntity = repository.findById(uuid).get();
            ENTITY entity = mapper.requestToEntity(request);
            entity.setUuid(uuid);
            entity.setCreatedAt(oldEntity.getCreatedAt());
            entity.setUser(oldEntity.getUser());
            ENTITY savedEntity = repository.save(entity);
            return mapper.entityToRatingDto(savedEntity);
        } else {
             throw new CustomException(HttpStatus.NOT_FOUND, "El recurso no existe");
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
            throw new CustomException(HttpStatus.NOT_FOUND, "El recurso no existe");
        repository.deleteById(uuid);
        return true;
    }

    @Override
    public boolean existsByUuidAndUserUsername(UUID uuid, String username) {
        return repository.existsByUuidAndUserUsername(uuid, username);
    }

    @Override
    public boolean existsByEntityUuidAndUserUsername(UUID entityUuid, String username) {
        return repository.existsByEntityUuidAndUserUsername(entityUuid, username);
    }
}
