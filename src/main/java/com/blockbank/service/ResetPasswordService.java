package com.blockbank.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blockbank.database.domain.Mail;
import com.blockbank.database.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
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
    private final Logger logger = LoggerFactory.getLogger(ResetPasswordService.class);

    private static final String SUBJECT = "Reset password";
    private static final String MESSAGE = """
            To reset your password, click the link below.
            This link will expire after 20 minutes.

            """;

    @Autowired
    public ResetPasswordService(RootRepository rootRepository, SaltGenerator saltGenerator, HashService hashService) {
        super();
        this.rootRepository = rootRepository;
        this.saltGenerator = saltGenerator;
        this.hashService = hashService;
        logger.info("New ResetPasswordService");
    }

    public Mail createResetMail(String recipient, String url, String token) {
        return new Mail(recipient, SUBJECT, MESSAGE + url + "/reset?token=" + token);
    }

    public String decodeTokenReturnUsername(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim("username").asString();
    }

    //user clicks link
    //verify token >
    //y: redirect to resetPassword.html

    //check if input password = identical
    //request to update password
}
