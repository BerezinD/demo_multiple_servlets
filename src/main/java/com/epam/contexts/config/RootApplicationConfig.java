package com.epam.contexts.config;

import com.epam.contexts.model.MessageToPass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackages = {"com.epam.contexts.service"})
public class RootApplicationConfig {

    @Bean
    public MessageToPass helloMessage() {
        MessageToPass messageToPass = new MessageToPass();
        messageToPass.setMessage("HELLO WORLD!");
        return messageToPass;
    }
}
