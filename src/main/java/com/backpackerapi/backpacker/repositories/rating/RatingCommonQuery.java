package com.backpackerapi.backpacker.repositories.rating;

import com.backpackerapi.backpacker.dtos.rating.SimplePunctuationDto;
import com.backpackerapi.backpacker.enums.EEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class RatingCommonQuery {

    @Autowired
    private EntityManager entityManager;

    public SimplePunctuationDto punctuationByEntityUuid(EEntity eEntity, UUID entityUuid){
        String q = "SELECT COUNT(*) population, COALESCE(ROUND(CAST(AVG(punctuation) AS NUMERIC), 2), 0) punctuation " +
                "FROM " + eEntity.name().toLowerCase() + " WHERE entity_fk =  :entityUuid";
        Query query = entityManager.createNativeQuery(q);
        query.setParameter("entityUuid", entityUuid);
        List<Object[]> result = query.getResultList();
        SimplePunctuationDto punctuation = new SimplePunctuationDto();
        if(!result.isEmpty()){
            Object[] r = result.get(0);
            punctuation.setPopulation(((Number) r[0]).intValue());
            punctuation.setPunctuation(((Number) r[1]).floatValue());
        }
        return punctuation;
    }

}
