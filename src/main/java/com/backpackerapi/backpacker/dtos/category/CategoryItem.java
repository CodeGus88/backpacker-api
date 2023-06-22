package com.backpackerapi.backpacker.dtos.category;

public class CategoryItem implements ICategoryItem{

    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
