package com.epam.contexts.first.web.application.controller;

import com.epam.contexts.service.MessagingService;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FirstWebAppController implements Controller {

    private static Logger logger = Logger.getLogger(FirstWebAppController.class.getName());

    private MessagingService messagingService;

    private WebApplicationContext webApplicationContext;

    private void processContext() {
        WebApplicationContext rootContext = ContextLoader.getCurrentWebApplicationContext();

        logger.log(Level.INFO,"Here root context: {0}", rootContext);
        logger.log(Level.INFO,"And root context Beans: {0}", Arrays.asList(rootContext.getBeanDefinitionNames()));

        logger.log(Level.INFO, "Then, application context: {0}", webApplicationContext);
        logger.log(Level.INFO,"And application context Beans: {0}", Arrays.asList(webApplicationContext.getBeanDefinitionNames()));
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        processContext();
        String message = "<br><div style='text-align:center;'>" + "<h3>Normal " + messagingService.getMessage() + "</h3></div>";
        return new ModelAndView("welcome", "message", message);
    }

    public MessagingService getMessagingService() {
        return messagingService;
    }

    public void setMessagingService(MessagingService messagingService) {
        this.messagingService = messagingService;
    }

    public WebApplicationContext getWebApplicationContext() {
        return webApplicationContext;
    }

    public void setWebApplicationContext(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }
}
