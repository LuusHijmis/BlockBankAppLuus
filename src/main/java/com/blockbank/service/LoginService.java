package com.blockbank.service;

/**
 * @author Alex Shijan
 */

import com.blockbank.database.domain.UserDetails;
import com.blockbank.database.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private RootRepository rootrepository;
    private HashService hashService;
    //private LoginValidationService loginValidationService;

    @Autowired
    //TODO: implement login validation service
    public LoginService(RootRepository rootrepository) {
        super();
        this.rootrepository = rootrepository;
    }

    //TODO IMPLEMENT a loginDTO

    // Does this return the authentication token?
    public boolean login(String username, String password) {
        if(validate(username, password)) {
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

    private boolean validate(String username, String password) {
        UserDetails userDetails = null;
        try {
            userDetails = rootrepository.findUserByUsername(username);
        } catch (Exception sqlException) {
            //TODO goed afhandelen foutmelding.
            sqlException.printStackTrace();
        }
        String hashedPassword = hashService.ultimateHash(password, userDetails.getSalt());
        if (userDetails.getUsername().equals(username) && userDetails.getPassword().equals(hashedPassword)) {
            return true;
        } else {
            return false;
        }
    }
}
