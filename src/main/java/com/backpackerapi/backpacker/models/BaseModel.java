package com.backpackerapi.backpacker.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import java.util.UUID;


@Data
@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue
    private UUID uuid;

}
