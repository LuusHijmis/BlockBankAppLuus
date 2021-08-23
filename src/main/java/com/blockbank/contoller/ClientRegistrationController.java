package com.blockbank.contoller;

/*@author Karish Resodikromo
 * Deze klasse behelst requesthandlers voor registreren van klanten */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ClientRegistrationController {


    private ClientRegistrationService clientregistrationservice;

    private final Logger logger = LoggerFactory.getLogger(ClientRegistrationController.class);

    @Autowired
    public ClientRegistrationController(ClientRegistrationService clientRegistrationService) {
        this.clientregistration = clientRegistrationService;
        logger.info("New ClientRegistrationController");
    }

    //TODO ClientRegistrationService maken + register methode
    //TODO PostMapping afmaken
    @PostMapping("/register")
    public User register(@RequestParam User user) {
        return clientregistrationservice.register(user);
    }

}

