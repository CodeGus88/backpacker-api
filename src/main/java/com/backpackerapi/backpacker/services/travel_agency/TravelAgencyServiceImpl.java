package com.backpackerapi.backpacker.services.travel_agency;

import com.backpackerapi.backpacker.dtos.travel_agency.ITravelAgencyItem;
import com.backpackerapi.backpacker.dtos.travel_agency.TravelAgencyDto;
import com.backpackerapi.backpacker.dtos.travel_agency.TravelAgencyRequest;
import com.backpackerapi.backpacker.exceptions.CustomException;
import com.backpackerapi.backpacker.mappers.TravelAgencyMapper;
import com.backpackerapi.backpacker.models.principal_models.TravelAgency;
import com.backpackerapi.backpacker.repositories.TravelAgencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TravelAgencyServiceImpl implements TravelAgencyService{

    private TravelAgencyRepository repository;
    private TravelAgencyMapper mapper;

    @Override
    public TravelAgencyDto findById(UUID uuid) {
        TravelAgency travelAgency = repository
                .findById(uuid).orElse(null);
        return mapper.entityToDto(travelAgency);
    }

    @Override
    public Page<ITravelAgencyItem> findAllByIsPublic(Pageable pageable, String search, boolean isPublic) {
        return repository.filterAllByIsPublicPageable(pageable, search, isPublic);
    }

    @Override
    public TravelAgencyDto save(TravelAgencyRequest request) {
        TravelAgency travelAgency = mapper.requestToEntity(request);
        travelAgency.setCreatedAt(Instant.now());
        TravelAgencyDto dto = mapper.entityToDto(repository.save(travelAgency));
        return dto;
    }

    @Override
    public TravelAgencyDto update(UUID uuid, TravelAgencyRequest request) {
        TravelAgency travelAgency = repository.findById(uuid).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "No se encontró el recurso"));
        travelAgency.setName(request.getName());
        travelAgency.setImageIcon(request.getImageIcon());
        travelAgency.setIsPublic(request.getIsPublic());
        travelAgency.setKeywords(request.getKeywords());
        travelAgency.setDescription(request.getDescription());
        travelAgency.setUpdatedAt(Instant.now());
        return mapper.entityToDto(repository.save(travelAgency));
    }

    @Override
    public boolean deleteById(UUID uuid) {
        if(!repository.existsByUuid(uuid))
            return false;
        repository.deleteById(uuid);
        return true;
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return repository.existsByUuid(uuid);
    }

    @Override
    public boolean existsByUuidAndUserUsername(UUID uuid, String username) {
        return repository.existsByUuidAndUserUsername(uuid, username);
    }
}
