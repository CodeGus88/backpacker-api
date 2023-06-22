package com.backpackerapi.backpacker.repositories.rating;

import com.backpackerapi.backpacker.dtos.rating.IEntityRatingDto;
import com.backpackerapi.backpacker.dtos.rating.IRatingItem;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@NoRepositoryBean
@MappedSuperclass
public interface BaseRatingRepository<E> extends JpaRepository<E, UUID> {

    IEntityRatingDto calculatePunctuation(UUID entityUuid);

    List<IRatingItem> findLastByEntityUuid(UUID entityUuid, long limit);

    Optional<IRatingItem> findByEntityUuidAndUserUuid(UUID entityUuid, UUID userUuid);

}
