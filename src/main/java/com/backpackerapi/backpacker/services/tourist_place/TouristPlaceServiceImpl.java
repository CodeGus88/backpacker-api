package com.backpackerapi.backpacker.services.tourist_place;

import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceDto;
import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceRequest;
import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceItem;
import com.backpackerapi.backpacker.mappers.TouristPlaceMapper;
import com.backpackerapi.backpacker.models.TouristPlace;
import com.backpackerapi.backpacker.repositories.TouristPlaceRepository;
import com.backpackerapi.backpacker.services.storange.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TouristPlaceServiceImpl implements TouristPlaceService {

    @Autowired
    private TouristPlaceRepository repository;

    @Autowired
    private TouristPlaceMapper mapper;

    @Autowired
    private StorageService storageService;

    @Override
    public TouristPlaceDto findById(UUID uuid) {
        Optional<TouristPlace> data = repository.findById(uuid);
        return data.map(touristPlace -> mapper.entityToDto(touristPlace)).orElse(null);
    }

    @Override
    public Set<TouristPlaceItem> findAll() {
        return repository.findAll()
                .stream()
                .map(entity -> mapper.entityToItem(entity))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<TouristPlaceItem> findAllPageable(Pageable pageable) {
        Set<TouristPlaceItem> list = repository.findByIsPublic(true)
                .stream().map(entity -> mapper.entityToItem(entity))
                .collect(Collectors.toSet());
        return list;
    }

    @Override
    public Page<TouristPlaceItem> findAllPublicPageable(Pageable pageable) {
        return repository.findByIsPublic(pageable, true).map(entity -> mapper.entityToItem(entity));
    }

    @Override
    public Page<TouristPlaceItem> searchPageable(Pageable pageable, String filter) {
        return repository.filterAllPublicPageable(pageable, filter.toLowerCase(), filter)
                .map(entity -> mapper.entityToItem(entity));
    }

    @Transactional
    @Override
    public TouristPlaceDto save(TouristPlaceRequest touristPlaceRequest) {
        TouristPlace touristPlace = mapper.requestToEntity(touristPlaceRequest);
        touristPlace.setCreatedAt(LocalDateTime.now());
        touristPlace = repository.save(touristPlace);
        return mapper.entityToDto(touristPlace);
    }

    @Transactional
    @Override
    public TouristPlaceDto update(UUID uuid, TouristPlaceRequest touristPlaceRequest) {
        TouristPlaceDto dto = null;
        if(repository.existsById(uuid)) {
            TouristPlace entity = mapper.requestToEntity(touristPlaceRequest);
            entity.setUuid(uuid);
            dto = mapper.entityToDto(
                    repository.save(entity)
            );
        }
        return dto;
    }

    @Transactional
    @Override
    public boolean deleteById(UUID uuid) {
        try{
            repository.deleteById(uuid);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }



    @Override
    public long count(){
        return repository.count();
    }

    @Override
    public boolean existById(UUID uuid) {
        return repository.existsById(uuid);
    }
}
