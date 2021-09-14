package com.blockbank.contoller;


import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class LogoutController {

    private final Logger logger = LoggerFactory.getLogger(LogoutController.class);

    public LogoutController() {
        logger.info("New LogoutController");
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logoutPage (@RequestBody HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return ResponseEntity.noContent().build();
    }
}
