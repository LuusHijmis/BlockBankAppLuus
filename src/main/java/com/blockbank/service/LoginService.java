package com.blockbank.service;

/**
 * @author Alex Shijan
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    private LoginValidationService loginValidationService;

    @Autowired
    public LoginService(LoginValidationService loginValidationService) {
        super();
        this.loginValidationService = loginValidationService;
        logger.info("New LoginService");
    }

    //TODO IMPLEMENT a loginDTO

    // Does this return the authentication token?
    public String login(String username, String password) {
        String token = null;
        if(loginValidationService.validate(username, password)) {
            // Roept TokenService aan een maakt een token.

            // Redirect naar de juist pagina. -> bewerken van fronte-end.
            return token;
        } else {
            //TODO foutmelding geven aan de gebruiker -> wrong username/password
            return null;
        }
    }
}
