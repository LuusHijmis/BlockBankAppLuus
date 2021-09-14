package com.blockbank.contoller;



/**@author Karish Resodikromo
 * Deze klasse behelst requesthandlers voor registreren van klanten
 */





import com.blockbank.database.domain.RegistrationDTO;
import com.blockbank.database.domain.UserDetails;
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


    @PostMapping("/registerDTO")
    public ResponseEntity<?> registerDTO(@RequestBody RegistrationDTO registrationDTO) {
        System.out.println(registrationDTO);
        UserDetails userDetails = clientregistrationservice.register(registrationDTO);
        if (userDetails != null) {
//            URI uri = URI.create(String.valueOf(userDetails));
//            return ResponseEntity.created(uri).build();
            return ResponseEntity.ok().body(userDetails);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
