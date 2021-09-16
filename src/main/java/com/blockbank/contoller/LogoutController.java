package com.blockbank.contoller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blockbank.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LogoutController {

    private TokenService tokenService;
    private final Logger logger = LoggerFactory.getLogger(LogoutController.class);

    public LogoutController(TokenService tokenService) {
        this.tokenService = tokenService;
        logger.info("New LogoutController");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutPage (@RequestHeader(name="Authorization") String token) {
        System.out.println("token");
        if (tokenService.verifyToken(token)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}

