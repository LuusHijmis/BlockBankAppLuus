package com.blockbank.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blockbank.database.domain.Mail;
import com.blockbank.database.domain.UserDetails;
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
    private final SaltGenerator saltGenerator;
    private final HashService hashService;
    private final PasswordValidationService passwordValidationService;
    private final Logger logger = LoggerFactory.getLogger(ResetPasswordService.class);

    private static final String SUBJECT = "Reset password";
    private static final String MESSAGE = "To reset your password, click the link below.\nThis link will expire after 20 minutes.\n";

    @Autowired
    public ResetPasswordService(RootRepository rootRepository, SaltGenerator saltGenerator, HashService hashService, PasswordValidationService passwordValidationService) {
        super();
        this.rootRepository = rootRepository;
        this.saltGenerator = saltGenerator;
        this.hashService = hashService;
        this.passwordValidationService = passwordValidationService;
        logger.info("New ResetPasswordService");
    }

    public Mail createResetMail(String recipient, String url, String token) {
        return new Mail(recipient, SUBJECT, MESSAGE + url + "/reset?token=" + token);
    }

    public String decodeTokenReturnUsername(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim("username").asString();
    }

    //TODO: do some
    public void validationCheckUserAndPassword(UserDetails user, String password) {
        if (user != null && passwordValidationService.isValid(password)) {
            user.setPassword(password);
            //do some more
        }
    }

    //TODO: idk...
    public void errorCheckUserAndPassword(UserDetails user, String password) {
        if (user == null) {
            System.out.println("Invalid link or token has expired"); //TODO: header/body van maken
            //return some msg / return false + msg?
        } else if (!passwordValidationService.isValid(password)) {
            System.out.println("Password does not match requirements"); //TODO: header/body van maken
            //return some msg / return false + msg?
        }
    }
}
