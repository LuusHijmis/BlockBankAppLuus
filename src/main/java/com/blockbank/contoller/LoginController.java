package com.blockbank.contoller;

import com.blockbank.database.domain.LoginDTO;
import com.blockbank.database.domain.UserDetails;
import com.blockbank.database.repository.RootRepository;
import com.blockbank.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * @author hannahvd
 */

@RestController
public class LoginController {

    private final LoginService loginService;
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
        logger.info("New LoginController");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) { //why type = <?>
        String jwtUser = loginService.login(loginDTO.getUsername(), loginDTO.getPassword());
        if (jwtUser != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(jwtUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect credentials");
        }
    }
        //URI location = URI.create(String.format("http://"));
          //In order to initiate a request, a Web browser will extract the hostname from the URI
          //and make use of a DNS resolver to obtain the Internet Protocol address for that authority.

        //authorisation
          //switch case per role
          //redirect to target-page (javascript?)

        //else {
           //user login rejected
           //clear password box and inform about unaccepted user-password combination (javascript?)
}

    /*
    https://blog.softtek.com/en/token-based-api-authentication-with-spring-and-jwt
     */