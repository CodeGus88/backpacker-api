package com.backpackerapi.backpacker.models.file;


import com.backpackerapi.backpacker.models.BaseModel;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseFile<E> extends BaseModel {

    @Column(unique = true)
    private String file;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @JoinColumn(name = "entity_uuid", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private E entity;

}
