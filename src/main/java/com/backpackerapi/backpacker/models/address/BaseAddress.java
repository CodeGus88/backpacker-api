package com.backpackerapi.backpacker.models.address;

import com.backpackerapi.backpacker.models.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseAddress<E extends BaseModel> extends BaseModel {
//public class BaseAddress<E> extends BaseModel {

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 100)
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private E entity;

}
