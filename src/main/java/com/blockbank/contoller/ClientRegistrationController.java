/*

package com.blockbank.contoller;


*/
/*@author Karish Resodikromo
 * Deze klasse behelst requesthandlers voor registreren van klanten *//*



import com.blockbank.database.domain.UserDTO;
import com.blockbank.service.ClientRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
public class ClientRegistrationController {


    private ClientRegistrationService clientregistrationservice;

    private final Logger logger = LoggerFactory.getLogger(ClientRegistrationController.class);

    @Autowired
    public ClientRegistrationController(ClientRegistrationService clientRegistrationService) {
        this.clientregistrationservice = clientRegistrationService;
        logger.info("New ClientRegistrationController");
    }


    //TODO PostMapping checken
    @PutMapping("/register")
    public ResponseEntity<?> register (@RequestBody UserDTO userDTO) {
        clientregistrationservice.register(userDTO);
        URI uri = URI.create(String.format("http://miw-team-4.nl/users/%d", userDTO.getUsername()));
        return ResponseEntity.created(uri)
                .body(userDTO);
    }
}


*/
