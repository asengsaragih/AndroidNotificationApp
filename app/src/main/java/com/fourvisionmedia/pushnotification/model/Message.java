package com.fourvisionmedia.pushnotification.model;

import java.io.Serializable;

public class Message implements Serializable {
    private String title;
    private String message;

    //construct kosong untuk string to object
    public Message() {
    }

    public Message(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }
}
