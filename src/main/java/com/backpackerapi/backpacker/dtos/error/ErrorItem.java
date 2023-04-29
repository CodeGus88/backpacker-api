package com.backpackerapi.backpacker.dtos.error;

public class ErrorItem{

    private String label;
    private String message;

    public ErrorItem(){ }

    public ErrorItem(String label, String message){
        this.label = label;
        this.message = message;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
