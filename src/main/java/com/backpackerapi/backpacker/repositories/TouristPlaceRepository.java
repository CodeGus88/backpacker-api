package com.backpackerapi.backpacker.repositories;

import com.backpackerapi.backpacker.models.TouristPlace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TouristPlaceRepository extends JpaRepository<TouristPlace, UUID> {

    Set<TouristPlace> findByIsPublic(boolean isPublic);

    Page<TouristPlace> findByIsPublic(Pageable pageable, boolean isPublic);

    @Query(value = "SELECT tp FROM TouristPlace tp " +
            "WHERE tp.isPublic = TRUE " +
            "AND (CAST(tp.uuid AS STRING) LIKE %:uuidFilter% "+
            "OR LOWER(tp.name) LIKE %:lowerFilter% " +
            "OR LOWER(tp.category) LIKE %:lowerFilter% " +
            "OR LOWER(tp.resume) LIKE %:lowerFilter% " +
            "OR LOWER(tp.keywords) LIKE %:lowerFilter%)"
    )
    Page<TouristPlace> filterAllPublicPageable(Pageable pageable, @Param("lowerFilter") String lowerFilter, @Param("uuidFilter") String uuidFilter);

}
