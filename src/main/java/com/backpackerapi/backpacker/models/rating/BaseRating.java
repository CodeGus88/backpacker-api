package com.backpackerapi.backpacker.models.rating;

import com.backpackerapi.backpacker.models.BaseModel;
import com.backpackerapi.backpacker.security.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseRating<E> extends BaseModel {

    private byte punctuation;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "entity_fk", nullable = false)
    private E entity;

    @ManyToOne
    @JoinColumn(name = "user_uuid", nullable = false)
    private User user;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
}
