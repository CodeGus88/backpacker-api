package com.backpackerapi.backpacker.security.entity;

import com.backpackerapi.backpacker.models.BaseModel;
import com.backpackerapi.backpacker.security.enums.ERole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role extends BaseModel {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ERole name;

    public Role() {
    }

    public Role(@NotNull ERole name) {
        this.name = name;
    }

}
