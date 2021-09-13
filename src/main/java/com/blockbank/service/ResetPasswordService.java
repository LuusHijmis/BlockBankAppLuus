package com.blockbank.service;

import com.blockbank.database.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hannahvd
 */

/*
https://www.baeldung.com/spring-security-registration-i-forgot-my-password
 */

@Service
public class ResetPasswordService {

    private final RootRepository rootRepository;
    private final Logger logger = LoggerFactory.getLogger(ResetPasswordService.class);

    private static final String RESET_MESSAGE = """
            click link to reset yo pw.

            ignore msg if u didn't ask.""";

    @Autowired
    public ResetPasswordService(RootRepository rootRepository) {
        super();
        this.rootRepository = rootRepository;
        logger.info("New ResetPasswordService");
    }

    //link gegenereerd bestaande uit een endpoint + jwt

    //parameter email
    //user found?
    //y: create token
    //n: redirect back to login.html + msg "e-mail-address not found"
    //send email
    //redirect success page

    //uh...
    private boolean findUserInDatabase(String userEmailadress) {
        return rootRepository.findUserByEmail(userEmailadress) != null;
    }

    //create token //create endpoint



}
