package com.backpackerapi.backpacker.repositories.rating;

import com.backpackerapi.backpacker.dtos.rating.IEntityRatingDto;
import com.backpackerapi.backpacker.dtos.rating.IRatingItem;
import com.backpackerapi.backpacker.models.rating.TravelAgencyRating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TravelAgencyRatingRepository extends BaseRatingRepository<TravelAgencyRating>{

    @Query(
            value = """
                SELECT CASE WHEN (COUNT(*) > 0) = TRUE THEN 'TRUE' ELSE 'FALSE' END
                FROM travel_agency_rating tar
                JOIN users u ON u.uuid = tar.user_id
                WHERE tar.entity_fk = :entityUuid AND tar. = :username
            """,
            nativeQuery = true
    )
    @Override
    boolean existsByEntityUuidAndUserUsername(UUID entityUuid, String username);

    @Query(
            value = """
                SELECT CASE WHEN (COUNT(*) > 0) = TRUE THEN 'TRUE' ELSE 'FALSE' END
                FROM travel_agency_rating tar
                WHERE tar.entity_fk = :entityUuid AND tar.user_uuid = :userUuid
            """,
            nativeQuery = true
    )
    @Override
    boolean existsByEntityUuidAndUserUuid(@Param("entityUuid") UUID entityUuid, @Param("userUuid") UUID userUuid);

    @Query(
        value = """
                SELECT ta.uuid entityUuid, COUNT(*) population, ROUND(CAST(AVG(r.punctuation) AS NUMERIC), 2) punctuation
                FROM travel_agency_rating r
                JOIN travel_agencies ta ON ta.uuid = r.entity_fk
                WHERE ta.uuid = :entityUuid
                GROUP BY ta.uuid
                """,
            nativeQuery = true
    )
    @Override
    IEntityRatingDto calculatePunctuation(@Param("entityUuid") UUID entityUuid);



    @Query(
            value = """
                    SELECT r.uuid, r.punctuation, r.comment, r.created_at createdAt, u.uuid userUuid, u.uuid userUuid, 
                    u.username, u.name, u.email
                    FROM travel_agency_rating r
                    INNER JOIN travel_agencies ta ON ta.uuid = r.entity_fk
                    INNER JOIN users u ON u.uuid = r.user_uuid
                    WHERE ta.uuid = :entityUuid
                    ORDER BY r.created_at DESC
                    LIMIT :limit
                    """,
            nativeQuery = true
    )
    @Override
    List<IRatingItem> findLastByEntityUuid(@Param("entityUuid") UUID entityUuid, @Param("limit") long limit);


    @Query(
            value = """
                    SELECT tar.uuid, tar.punctuation, tar.comment, u.uuid userUuid,
                           u.name, u.username, u.email, tar.created_at createdAt
                    FROM travel_agency_rating tar
                    INNER JOIN travel_agencies e ON e.uuid = tar.entity_fk
                    INNER JOIN users u ON u.uuid = tar.user_uuid
                    WHERE e.uuid = :entityUuid AND u.uuid = :userUuid
                    """,
            nativeQuery = true
    )
    @Override
    Optional<IRatingItem> findByEntityUuidAndUserUuid(@Param("entityUuid") UUID entityUuid, @Param("userUuid") UUID userUuid);

}
