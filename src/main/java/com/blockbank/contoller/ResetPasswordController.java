package com.blockbank.contoller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blockbank.database.domain.Mail;
import com.blockbank.database.domain.UserDetails;
import com.blockbank.database.repository.RootRepository;
import com.blockbank.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author hannahvd
 */

@RestController
public class ResetPasswordController {

    private final Logger logger = LoggerFactory.getLogger(ResetPasswordController.class);
    private final RootRepository rootRepository;
    private final TokenService tokenService;
    private final ResetPasswordService resetService;
    private final SendMailServiceImpl mailService;

    @Autowired
    public ResetPasswordController(RootRepository rootRepository, TokenService tokenService, ResetPasswordService resetService, SendMailServiceImpl mailService) {
        this.rootRepository = rootRepository;
        this.tokenService = tokenService;
        this.resetService = resetService;
        this.mailService = mailService;
    }

    @PostMapping("/forgot")
    public ResponseEntity<?> sendPasswordResetMail(@RequestBody String email, HttpServletRequest request) { //<String>
        logger.info("New reset password for e-mail address: " + email);
        UserDetails user = rootRepository.findUserByEmail(email);
        if (user != null) {
            String token = tokenService.issueToken(user.getUsername());
            String url = request.getScheme() + "://" + request.getServerName();
            Mail msg = resetService.createResetMail(email, url, token);
            mailService.sendMail(msg);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Password reset link successfully sent to " + email);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No account has been found for " + email); //I_AM_A_TEAPOT :''''') ?
        }
    }

    @GetMapping("/reset") //only String data types
    public ModelAndView loadResetPasswordPage(final ModelMap model, @RequestParam String token) {
        logger.info("New reset password link");
        if (tokenService.verifyToken(token)) {
            //String username = resetService.decodeTokenReturnUsername(token);
            model.addAttribute("token", token);
            return new ModelAndView("redirect:/resetPassword.html", model);
        }
        logger.info("User was null");
        return new ModelAndView("redirect:/index.html", model);
    }

    @PostMapping("/update") //or ("/reset") as well? //PutMapping?
    public ResponseEntity<?> updateNewPassword(@RequestParam String password, @RequestParam String email) { //hoe kom ik aan die email (zonder dat gebruiker invult?) //token?
        logger.info("Update new password");
        UserDetails user = rootRepository.findUserByEmail(email);
        if (user != null) {
            user.setPassword(password);
            rootRepository.updatePassword(user);
            return ResponseEntity.status(HttpStatus.OK).body("Password successfully updated");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request /Bla");
        }
    }

    //set new password;
    //save user;
    //set reset token to null so it cannot be used again;
    //redirect user to success-page / give success-alert;
    //else {
    //error msg "invalid link or time has expired"

}
