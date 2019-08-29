package com.epam.contexts.model;

public class MessageToPass {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageToPass{" +
                "message='" + message + '\'' +
                '}';
    }
}
