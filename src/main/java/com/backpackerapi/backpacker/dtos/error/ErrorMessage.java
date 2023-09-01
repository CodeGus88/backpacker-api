package com.backpackerapi.backpacker.dtos.error;


import com.backpackerapi.backpacker.dtos.Message;

import java.util.Set;

public class ErrorMessage extends Message {

    private Set<ErrorItem> errorItems;

    public ErrorMessage(){}

    public ErrorMessage(String parentMessage, Set<ErrorItem> errorItems){
        super(parentMessage);
        this.errorItems = errorItems;
    }

    public Set<ErrorItem> getErrorItems() {
        return errorItems;
    }

    public void setErrorItems(Set<ErrorItem> errorItems) {
        this.errorItems = errorItems;
    }
}




