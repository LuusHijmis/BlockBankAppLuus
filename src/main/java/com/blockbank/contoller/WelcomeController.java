package com.blockbank.contoller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blockbank.service.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex Shijan
 */
@RestController
public class WelcomeController {

    private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
    private TokenService tokenService;
    private ObjectMapper mapper;

    @Autowired
    public WelcomeController(TokenService tokenService, ObjectMapper mapper){
        super();
        this.tokenService = tokenService;
        this.mapper = mapper;
        logger.info("New WelcomeController");
    }

    @GetMapping("/welcome/get/json")
    public ResponseEntity<?> loadWelcomePage(@RequestHeader(name="Authorization") String token) throws JsonProcessingException {
        System.out.println(token);
        if(tokenService.verifyToken(token)) {
            DecodedJWT decodedJWT = JWT.decode(token);
            Map<String, Object> payload = new HashMap();
            payload.put("username", decodedJWT.getClaim("username").asString());
            payload.put("role", decodedJWT.getClaim("role").asString());
            String json = mapper.writeValueAsString(payload);
            return ResponseEntity.ok(json);
        } else {
            return ResponseEntity.status(403).build();
        }
    }
}
