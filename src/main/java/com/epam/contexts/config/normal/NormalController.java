package com.epam.contexts.config.normal;

import com.epam.contexts.service.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class NormalController {

    private static Logger logger = Logger.getLogger(NormalController.class.getName());

    @Autowired
    private MessagingService messagingService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private void processContext() {
        WebApplicationContext rootContext = ContextLoader.getCurrentWebApplicationContext();

        logger.log(Level.INFO,"Here root context: " + rootContext);
        logger.log(Level.INFO,"And root context Beans: " + Arrays.asList(rootContext.getBeanDefinitionNames()));

        logger.log(Level.INFO, "Then, application context: " + webApplicationContext);
        logger.log(Level.INFO,"And application context Beans: " + Arrays.asList(webApplicationContext.getBeanDefinitionNames()));
    }

    @GetMapping(path = "/welcome")
    public ModelAndView example() {
        processContext();
        String message = "<br><div style='text-align:center;'>" + "<h3>Normal " + messagingService.getMessage() + "</h3></div>";
        return new ModelAndView("welcome", "message", message);
    }
}
