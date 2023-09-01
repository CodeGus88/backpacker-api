package com.backpackerapi.backpacker.repositories;

import com.backpackerapi.backpacker.dtos.travel_agency.ITravelAgencyItem;
import com.backpackerapi.backpacker.models.principal_models.TravelAgency;
import com.backpackerapi.backpacker.repositories.query_rewriter.TPQueryRewriter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface TravelAgencyRepository extends JpaRepository<TravelAgency, UUID> {

    Set<TravelAgency> findByIsPublic(boolean isPublic);

    @Query(value = """
            SELECT ta.uuid, ta.name, ta.image_icon imageIcon, 
            ta.created_at createdAt, ROUND(CAST(AVG(COALESCE(tar.punctuation, 0)) AS NUMERIC), 1) rating
            FROM travel_agencies ta
            LEFT JOIN travel_agency_rating tar ON tar.entity_fK = ta.uuid
            WHERE ta.is_public = :isPublic
            AND (
                LOWER(ta.name) LIKE LOWER(CONCAT('%', :filter, '%')) OR
                LOWER(ta.keywords) LIKE LOWER(CONCAT('%', :filter, '%')) OR
                CONCAT(ta.uuid, '') LIKE CONCAT('%', :filter, '%') OR
                CONCAT(ta.created_at, '') LIKE CONCAT('%', :filter, '%')
            )
            GROUP BY ta.uuid
            """,
            countQuery = """
                    SELECT COUNT(ta.uuid) 
                    FROM travel_agencies ta
                    WHERE ta.is_public = :isPublic 
                    AND (
                        LOWER(ta.name) LIKE LOWER(CONCAT('%', :filter, '%')) OR 
                        LOWER(ta.keywords) LIKE LOWER(CONCAT('%', :filter, '%')) OR 
                        CONCAT(ta.uuid, '') LIKE CONCAT('%', :filter, '%') OR 
                        CONCAT(ta.created_at, '') LIKE CONCAT('%', :filter, '%')
                    )
                    GROUP BY ta.uuid
                    """,
            nativeQuery = true,
            queryRewriter = TPQueryRewriter.class
    )
    Page<ITravelAgencyItem> filterAllByIsPublicPageable(
            Pageable pageable,
            @Param("filter") String filter,
            @Param("isPublic") boolean isPublic
    );

    boolean existsByUuid(UUID uuid);

    boolean existsByUuidAndUserUsername(UUID uuid, String username);

}
