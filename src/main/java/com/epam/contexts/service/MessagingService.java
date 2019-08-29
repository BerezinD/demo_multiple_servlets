package com.epam.contexts.service;

import com.epam.contexts.model.MessageToPass;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessagingService {

    @Resource
    private MessageToPass message;

    public String getMessage() {
        return message.getMessage();
    }
}
