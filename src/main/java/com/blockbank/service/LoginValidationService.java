package com.blockbank.service;

/**
 * @author Alex Shijan
 */

import com.blockbank.database.domain.UserDetails;
import com.blockbank.database.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginValidationService {

    private final Logger logger = LoggerFactory.getLogger(LoginValidationService.class);

    private RootRepository rootRepository;
    private HashService hashService;

    @Autowired
    public LoginValidationService(RootRepository rootRepository, HashService hashService) {
        super();
        this.rootRepository = rootRepository;
        this.hashService = hashService;
        logger.info("New LoginValidationService");
    }

    public boolean validate(String username, String password) {
        UserDetails userDetails = null;
        try {
            userDetails = rootRepository.findUserByUsername(username);
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

    //TODO token validation
}
