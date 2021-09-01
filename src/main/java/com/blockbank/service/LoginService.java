package com.blockbank.service;

/**
 * @author Alex Shijan
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private LoginValidationService loginValidationService;

    @Autowired
    public LoginService(LoginValidationService loginValidationService) {
        super();
        this.loginValidationService = loginValidationService;
    }

    //TODO IMPLEMENT a loginDTO

    // Does this return the authentication token?
    public boolean login(String username, String password) {
        if(loginValidationService.validate(username, password)) {
            // Aan frontend doorgeven dat inloggen gelukt is?

            // Token service aanroepen?

            // rol check?

            // Redirect naar de juist pagina. -> bewerken van fronte-end.
            return true;
        } else {
            // foutmelding geven aan de gebruiker -> wrong username/password
            return false;
        }
    }
}
