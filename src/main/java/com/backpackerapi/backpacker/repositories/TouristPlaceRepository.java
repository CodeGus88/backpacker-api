package com.backpackerapi.backpacker.repositories;

import com.backpackerapi.backpacker.dtos.tourist_place.ITouristPlaceItem;
import com.backpackerapi.backpacker.models.TouristPlace;
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
public interface TouristPlaceRepository extends JpaRepository<TouristPlace, UUID> {

    Set<TouristPlace> findByIsPublic(boolean isPublic);

    Page<TouristPlace> findByIsPublic(Pageable pageable, boolean isPublic);

    @Query(value = """
            SELECT tp.uuid, tp.name, tp.resume, tp.image_icon imageIcon, 
            tp.created_at createdAt, ROUND(AVG(tpr.punctuation), 1) rating, 
            STRING_AGG(DISTINCT cat.name, ',') categories
            FROM tourist_places tp
            LEFT JOIN tourist_places_rating tpr ON tpr.entity_fK = tp.uuid 
            LEFT JOIN tourist_places_categories joinTable ON joinTable.tourist_place_uuid = tp.uuid
            LEFT JOIN categories cat ON cat.id = joinTable.category_id 
            WHERE tp.is_public = TRUE
            AND (
                LOWER(tp.name) LIKE LOWER(CONCAT('%', :filter, '%')) OR
                LOWER(tp.keywords) LIKE LOWER(CONCAT('%', :filter, '%')) OR 
                CONCAT(tp.uuid, '') LIKE '%:filter%' OR 
                CONCAT(tp.created_at, '') LIKE '%:filter%' OR 
                LOWER(cat.name) LIKE LOWER(CONCAT('%', :filter, '%'))
            )
            GROUP BY tp.uuid
            """,
            countQuery = """
                    SELECT COUNT(tp.uuid) 
                    FROM tourist_places tp 
                    LEFT JOIN tourist_places_categories joinTable ON tp.uuid = tourist_place_uuid 
                    LEFT JOIN categories cat ON cat.id = joinTable.category_id 
                    WHERE tp.is_public = TRUE 
                    AND (
                        LOWER(tp.name) LIKE LOWER(CONCAT('%', :filter, '%')) OR 
                        LOWER(tp.keywords) LIKE LOWER(CONCAT('%', :filter, '%')) OR 
                        CONCAT(tp.uuid, '') LIKE '%:filter%' OR 
                        CONCAT(tp.created_at, '') LIKE '%:filter%' OR 
                        LOWER(cat.name) LIKE LOWER(CONCAT('%', :filter, '%'))
                    )
                    GROUP BY tp.uuid
                    """,
            nativeQuery = true,
            queryRewriter = TPQueryRewriter.class
    )
    Page<ITouristPlaceItem> filterAllPublicPageable(
            Pageable pageable,
            @Param("filter") String filter
    );

}
