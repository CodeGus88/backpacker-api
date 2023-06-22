package com.backpackerapi.backpacker.repositories.query_rewriter;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.QueryRewriter;

public class TPQueryRewriter implements QueryRewriter {
    @Override
    public String rewrite(String query, Sort sort) {
        return query
                .replaceAll("tp.rating", "rating")
                .replaceAll("createdAt", "created_at");
    }
}
