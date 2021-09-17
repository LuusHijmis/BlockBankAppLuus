package com.blockbank.service;

/**
 * @author Alex Shijan
 */

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.blockbank.database.domain.UserDetails;
import com.blockbank.database.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    private RootRepository rootRepository;

    private final String BANK_NAME = "Blockbank";
    //TODO -> generate Issue at en Expire at aan de hand van de huidige tijd.
    private Date iat;
    private Date exp;

    private final Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Autowired
    public TokenService(RootRepository rootRepository) {
        super();
        this.rootRepository = rootRepository;
        logger.info("New TokenService");
    }

    public String issueToken(String username) {
        String token = null;
        iat = new Date(System.currentTimeMillis());
        exp = new Date(System.currentTimeMillis() + (20 * 60 * 1000));
        UserDetails user = rootRepository.findUserByUsername(username);

        Map<String, Object> payload = new HashMap();
        payload.put("username", user.getUsername());
        payload.put("role", user.getRole());
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            token = JWT.create().withIssuer(BANK_NAME)
                    .withPayload(payload)
                    .withIssuedAt(iat)
                    .withExpiresAt(exp)
                    .sign(algorithm);
            logger.info("Token Issued");
        } catch (JWTCreationException exception){
            System.out.println(exception.getMessage());
        }
        return token;
    }

    public boolean verifyToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            Verification verifier = JWT.require(algorithm);
            verifier.build().verify(decodedJWT);
        } catch (JWTVerificationException exception){
            return false;
        }
        return true;
    }

    public Date getIat() {
        return iat;
    }

    public Date getExp() {
        return exp;
    }
}
