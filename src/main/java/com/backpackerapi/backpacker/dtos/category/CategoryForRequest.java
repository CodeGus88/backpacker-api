package com.backpackerapi.backpacker.dtos.category;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryForRequest {

    @NotNull
    private long id;

}
