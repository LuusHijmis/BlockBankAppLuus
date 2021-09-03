package com.blockbank.contoller;

import com.blockbank.database.domain.LoginDTO;
import com.blockbank.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * @author hannahvd
 */

//notities:

    //Na een eerste authenticatie met gebruikersnaam of wachtwoord geeft de server een token terug
      //die vervolgens gebruikt wordt als credential.
    //...kun je token veilig(er) automatisch aan een request toevoegen zonder dat een gebruiker
      //zich voor elke request handmatig dient te authenticeren.
    //Je moet zowel gebruikersnaam-wachtwoord-combinatie alswel een gegenereerd token kunnen authenticeren.
      //Je hebt dan een aparte login methode nodig, die een token teruggeeft. En de authenticate() methode
      //moet nu een token gaan verwerken in plaats van een gebruikersnaam een wachtwoord.
    /*
    To keep them secure, you should always store JWTs inside an httpOnly cookie. This is a special kind
      of cookie that’s only sent in HTTP requests to the server. It’s never accessible (both for reading
      or writing) from JavaScript running in the browser.
     */

@RestController
public class LoginController {

    private LoginService loginService;
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

//    @Value("${jwt.secret}")
//    private String jwtSecret;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
        logger.info("New LoginController");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        String jwtUser = loginService.login(loginDTO.getUsername(), loginDTO.getPassword());

        //authentication
        if (jwtUser != null) {
            //bla //return response.OK
        } else {
            //return response.niet OK    (403 = login failed)
        }

        //return ResponseEntity.status(HttpStatus.CREATED).body(JwtTokenGenerator.generateToken(jwtUserDto, jwtSecret));

        //authorisation
          //switch case per role
            //redirect to target-page


        //else {
           //user login rejected
           //clear password box and inform about unaccepted user-password combination


        //URI location = URI.create(String.format("http://"));

        return ResponseEntity.badRequest().build();
    }

}

/*
    @RequestMapping(value = "/login_process", method = RequestMethod.POST)
    public ModelAndView loginProcess(@RequestParam("nick") final String username,
                                     @RequestParam("passwd") final String password,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        try {
            UserDetailsImpl details = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
            token.setDetails(details);
            Authentication auth = authenticationManager.authenticate(token);
            UserDetailsImpl userDetails = (UserDetailsImpl)auth.getDetails();

            if(!userDetails.getUser().isActivated()) {
                return new ModelAndView(new RedirectView("/login.jsp?error=not_activated"));
            } else {
                SecurityContextHolder.getContext().setAuthentication(auth);
                rememberMeServices.loginSuccess(request, response, auth);
                AuthUtil.updateLastLogin(auth, userDao);

                return new ModelAndView(new RedirectView("/"));
            }

        } catch (LockedException | BadCredentialsException | UsernameNotFoundException e) {
            logger.warn("Login of " + username + " failed; remote IP: "+request.getRemoteAddr()+"; " + e.toString());
            return new ModelAndView(new RedirectView("/login.jsp?error=true"));
        }
    }
*/

/*
This can also be used in Spring MVC as the return value from an @Controller method:
 @RequestMapping("/handle")
 public ResponseEntity<String> handle() {
   URI location = ...;
   HttpHeaders responseHeaders = new HttpHeaders();
   responseHeaders.setLocation(location);
   responseHeaders.set("MyResponseHeader", "MyValue");
   return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED);
 }

Or, by using a builder accessible via static methods:
 @RequestMapping("/handle")
 public ResponseEntity<String> handle() {
   URI location = ...;
   return ResponseEntity.created(location).header("MyResponseHeader", "MyValue").body("Hello World");
 }
 */