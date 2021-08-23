package com.blockbank.contoller;

/*@author Karish Resodikromo
 * Deze klasse behelst requesthandlers voor registreren van klanten */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;


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
    //TODO PostMapping checken
    @PostMapping("/register")
    ResponseEntity<?> registerUser(@RequestBody User user) {
        clientregistrationservice.register(user);
        URI uri = URI.create(String.format("http://miw-team-4.nl/users/%d", user.getId()));
        return ResponseEntity.created(uri)
                .body(user);
    }
}

