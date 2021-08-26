

package com.blockbank.contoller;



/**@author Karish Resodikromo
 * Deze klasse behelst requesthandlers voor registreren van klanten
 */





import com.blockbank.database.domain.UserDTO;
import com.blockbank.database.domain.UserDetails;
import com.blockbank.service.ClientRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


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
    public ResponseEntity<?> register(@RequestParam String firstname, @RequestParam String prefix,
                                      @RequestParam String lastname, @RequestParam LocalDate dateOfBirth,
                                      @RequestParam int bsn, @RequestParam String emailAddress,
                                      @RequestParam String username, @RequestParam String password,
                                      @RequestParam String address,
                                      @RequestParam int houseNumber, @RequestParam String affix,
                                      @RequestParam String postalCode, @RequestParam String city,
                                      @RequestParam String country) {
        UserDTO userDTO = null;
        if (clientregistrationservice.register(userDTO)) {
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}



