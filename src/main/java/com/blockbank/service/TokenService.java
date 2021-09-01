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
public class TokenService {

    RootRepository rootRepository;

    private final String BANK_NAME = "Blockbank";
    private final String AUDIENCE = "www.blockbank.com";
    //TODO -> generate Issue at en Expire at aan de hand van de huidige tijd.
    private int iat = 1630497103;
    private int exp = 1630498301;

    private final Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Autowired
    public TokenService(RootRepository rootRepository) {
        super();
        this.rootRepository = rootRepository;
        logger.info("New TokenService");
    }

    public String issueToken(String username) {
        UserDetails userDetails = rootRepository.findUserByUsername(username);

        String jsonString = String.format(
                "{\"iss\": \"%s\",\"iat\": %d,\"exp\": %d,\"aud\": \"%s\",\"sub\": \"%s\",\"Role\": \"%s\"}"
                , BANK_NAME, iat, exp, AUDIENCE, userDetails.getUsername(), userDetails.getRole());

        logger.info("Token Issued");
        return jsonString;
    }
}
