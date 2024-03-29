package com.backpackerapi.backpacker.repositories.rating;

import com.backpackerapi.backpacker.dtos.rating.IEntityRatingDto;
import com.backpackerapi.backpacker.dtos.rating.IRatingItem;
import com.backpackerapi.backpacker.models.rating.TouristPlaceRating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TouristPlaceRatingRepository extends BaseRatingRepository<TouristPlaceRating>{

    @Query(
            value = """
                SELECT CASE WHEN (COUNT(*) > 0) = TRUE THEN 'TRUE' ELSE 'FALSE' END
                FROM tourist_place_rating tpr
                JOIN users u ON u.uuid = tpr.user_id
                WHERE tpr.entity_fk = :entityUuid AND tpr. = :username
            """,
            nativeQuery = true
    )
    @Override
    boolean existsByEntityUuidAndUserUsername(UUID entityUuid, String username);

    @Query(
            value = """
                SELECT CASE WHEN (COUNT(*) > 0) = TRUE THEN 'TRUE' ELSE 'FALSE' END
                FROM tourist_place_rating tpr
                WHERE tpr.entity_fk = :entityUuid AND tpr.user_uuid = :userUuid
            """,
            nativeQuery = true
    )
    @Override
    boolean existsByEntityUuidAndUserUuid(@Param("entityUuid") UUID entityUuid, @Param("userUuid") UUID userUuid);

    @Query(
        value = """
                SELECT tp.uuid entityUuid, COUNT(*) population, ROUND(CAST(AVG(r.punctuation) AS NUMERIC), 2) punctuation
                FROM tourist_place_rating r
                JOIN tourist_places tp ON tp.uuid = r.entity_fk
                WHERE tp.uuid = :entityUuid
                GROUP BY tp.uuid
                """,
            nativeQuery = true
    )
    @Override
    IEntityRatingDto calculatePunctuation(@Param("entityUuid") UUID entityUuid);



    @Query(
            value = """
                    SELECT r.uuid, r.punctuation, r.comment, r.created_at createdAt, u.uuid userUuid, u.uuid userUuid, 
                    u.username, u.name, u.email
                    FROM tourist_place_rating r
                    INNER JOIN tourist_places tp ON tp.uuid = r.entity_fk
                    INNER JOIN users u ON u.uuid = r.user_uuid
                    WHERE tp.uuid = :entityUuid
                    ORDER BY r.created_at DESC
                    LIMIT :limit
                    """,
            nativeQuery = true
    )
    @Override
    List<IRatingItem> findLastByEntityUuid(@Param("entityUuid") UUID entityUuid, @Param("limit") long limit);


    @Query(
            value = """
                    SELECT tpr.uuid, tpr.punctuation, tpr.comment, u.uuid userUuid,
                           u.name, u.username, u.email, tpr.created_at createdAt
                    FROM tourist_place_rating tpr
                    INNER JOIN tourist_places e ON e.uuid = tpr.entity_fk
                    INNER JOIN users u ON u.uuid = tpr.user_uuid
                    WHERE e.uuid = :entityUuid AND u.uuid = :userUuid
                    """,
            nativeQuery = true
    )
    @Override
    Optional<IRatingItem> findByEntityUuidAndUserUuid(@Param("entityUuid") UUID entityUuid, @Param("userUuid") UUID userUuid);

}
