package com.example.admin.andoridthreads;

/**
 * Created by Admin on 8/8/2017.
 */

public class MessageEvent {
    String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
