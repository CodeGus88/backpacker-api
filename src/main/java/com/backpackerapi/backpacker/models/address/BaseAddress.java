package com.backpackerapi.backpacker.models.address;

import com.backpackerapi.backpacker.models.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseAddress<E extends BaseModel> extends BaseModel {

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 250)
    private String address;

    @Column(length = 30)
    private String continent;

    @Column(length = 30)
    private String country;

    @Column(length = 30)
    private String state;

    @Column(length = 30)
    private String county;

    @Column(nullable = false)
    private double lat;

    @Column(nullable = false)
    private double lng;

    @ManyToOne(fetch = FetchType.LAZY)
    private E entity;

}
