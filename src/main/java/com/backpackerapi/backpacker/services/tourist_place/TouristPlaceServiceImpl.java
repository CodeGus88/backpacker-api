package com.backpackerapi.backpacker.services.tourist_place;

import com.backpackerapi.backpacker.dtos.tourist_place.ITouristPlaceItem;
import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceDto;
import com.backpackerapi.backpacker.dtos.tourist_place.TouristPlaceRequest;
import com.backpackerapi.backpacker.mappers.TouristPlaceMapper;
import com.backpackerapi.backpacker.models.Category;
import com.backpackerapi.backpacker.models.principal_models.TouristPlace;
import com.backpackerapi.backpacker.repositories.CategoryRepository;
import com.backpackerapi.backpacker.repositories.TouristPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

@Service
public class TouristPlaceServiceImpl implements TouristPlaceService {

    @Autowired
    private TouristPlaceRepository repository;

    @Autowired
    private CategoryRepository catRepository;

    @Autowired
    private TouristPlaceMapper mapper;

    @Override
    public TouristPlaceDto findById(UUID uuid) {
        Optional<TouristPlace> data = repository.findById(uuid);
        return data.map(touristPlace -> mapper.entityToDto(touristPlace)).orElse(null);
    }

    @Override
    public Page<ITouristPlaceItem> findAllByIsPublic(Pageable pageable, String filter, boolean isPublic) {
        return repository.filterAllByIsPublicPageable(pageable, filter, isPublic);
    }

    @Transactional
    @Override
    public TouristPlaceDto save(TouristPlaceRequest touristPlaceRequest) {
        TouristPlace touristPlace = mapper.requestToEntity(touristPlaceRequest);
//        touristPlace.setCreatedAt(LocalDateTime.now());
//        touristPlace.setCreatedAt(ZonedDateTime.now(ZoneId.of("UTC")));
        touristPlace.setCreatedAt(Instant.now());
        List<Category> categories = new ArrayList<>();
        touristPlaceRequest.getCategories().forEach(i -> {
            Optional<Category> o = catRepository.findById(i);
            if(o.isPresent())
                categories.add(o.get());
        });
        touristPlace.setCategories(categories);
        touristPlace = repository.save(touristPlace);
        return mapper.entityToDto(touristPlace);
    }

    @Transactional
    @Override
    public TouristPlaceDto update(UUID uuid, TouristPlaceRequest request) {
        TouristPlaceDto dto = null;
        if (repository.existsById(uuid)) {
            TouristPlace entity = repository.findById(uuid).get();
            entity.setName(request.getName());
            entity.setImageIcon(request.getImageIcon());
            entity.setIsPublic(request.getIsPublic());
            entity.setResume(request.getResume());
            entity.setKeywords(request.getKeywords());
            entity.setDescription(request.getDescription());
//            entity.setUpdatedAt(LocalDateTime.now());
//            entity.setUpdatedAt(ZonedDateTime.now(ZoneId.of("UTC")));
            entity.setUpdatedAt(Instant.now());
            if(request.getCategories() != null){
                List<Category> categories = new ArrayList<>();
                request.getCategories().forEach(i -> {
                    Optional<Category> o = catRepository.findById(i);
                    if(o.isPresent())
                        categories.add(o.get());
                });
                entity.setCategories(categories);
            }
            dto = mapper.entityToDto(
                    repository.save(entity)
            );
        }
        return dto;
    }

    @Transactional
    @Override
    public boolean deleteById(UUID uuid) throws RuntimeException {
        if(!repository.existsById(uuid))
            return false;
        repository.deleteById(uuid);
        return true;
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public boolean existById(UUID uuid) {
        return repository.existsById(uuid);
    }
}
