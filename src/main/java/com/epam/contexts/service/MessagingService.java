package com.epam.contexts.service;

import com.epam.contexts.model.MessageToPass;

public class MessagingService {

    private MessageToPass message;

    public String getMessage() {
        return message.getMessage();
    }

    public void setMessage(MessageToPass message) {
        this.message = message;
    }
}
