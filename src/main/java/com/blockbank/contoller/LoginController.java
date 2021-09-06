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
    private final RootRepository rootRepository;
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

//    @Value("${jwt.secret}")
//    private String jwtSecret; //? //kan weg?

    @Autowired
    public LoginController(LoginService loginService, RootRepository rootRepository) {
        this.loginService = loginService;
        this.rootRepository = rootRepository;
        logger.info("New LoginController");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) { //why type = <?>
        String jwtUser = loginService.login(loginDTO.getUsername(), loginDTO.getPassword());
        UserDetails allDetails = rootRepository.findUserByUsername(loginDTO.getUsername());
        String role = allDetails.getRole();
        if (jwtUser != null) {
            HttpHeaders h = new HttpHeaders();
            h.add("token", jwtUser); //h.set? //set overwrites (if already added)
            h.add("role", role);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .headers(h)
                    .body("Welcome " + loginDTO.getUsername() + "!"); //? not sure
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect credentials");
            //401 Unauthorized response should be used for missing or bad authentication,
            //and a 403 Forbidden response should be used afterwards, when the user is authenticated but isn't authorized to perform the requested operation on the given resource
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