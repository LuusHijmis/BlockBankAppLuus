package com.blockbank.contoller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blockbank.database.domain.Asset;
import com.blockbank.database.domain.PortfolioDTO;
import com.blockbank.service.PortfolioService;
import com.blockbank.service.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class portfolioController {

    private PortfolioService portfolioService;
    private ObjectMapper mapper;
    private TokenService tokenService;

    private final Logger logger = LoggerFactory.getLogger(portfolioController.class);

    @Autowired
    public portfolioController(PortfolioService portfolioService, ObjectMapper mapper, TokenService tokenService) {
        this.portfolioService = portfolioService;
        this.mapper = mapper;
        this.tokenService = tokenService;
    }

    @GetMapping("/portfolio/get/json")
    public ResponseEntity<?> loadPortfolioOverview(@RequestHeader(name="Authorization") String token) throws JsonProcessingException {
        if(tokenService.verifyToken(token)) {
            DecodedJWT decodedJWT = JWT.decode(token);
            String username = decodedJWT.getClaim("username").asString();
            int userID = portfolioService.getUserIDbyName(username);
            System.out.println(userID);
            List<PortfolioDTO> portfolio = portfolioService.generatePortfolioList(userID);
            String json = mapper.writeValueAsString(portfolio);
            System.out.println(json);
//            String alteredJson = json.replaceAll("=", ":");
//            alteredJson = "["+alteredJson+"]";
//            System.out.println(alteredJson);
            return ResponseEntity.ok(json);
        } else {
            return ResponseEntity.status(403).build();
        }
    }

}
