package com.blockbank.contoller;

import com.blockbank.database.domain.Mail;
import com.blockbank.database.domain.UserDetails;
import com.blockbank.database.repository.RootRepository;
import com.blockbank.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hannahvd
 */

//@RestController
@Controller
public class ResetPasswordController {

    private final Logger logger = LoggerFactory.getLogger(ResetPasswordController.class);
    private final RootRepository rootRepository;
    private final TokenService tokenService;
    private final ResetPasswordService resetService;
    private final SendMailServiceImpl mailService;
    private final PasswordValidationService passwordValidationService;

    @Autowired
    public ResetPasswordController(RootRepository rootRepository, TokenService tokenService, ResetPasswordService resetService, SendMailServiceImpl mailService, PasswordValidationService passwordValidationService) {
        this.rootRepository = rootRepository;
        this.tokenService = tokenService;
        this.resetService = resetService;
        this.mailService = mailService;
        this.passwordValidationService = passwordValidationService;
    }

    @PostMapping("/forgot")
    public ResponseEntity<?> sendPasswordResetMail(@RequestBody String email, HttpServletRequest request) { //<String>
        logger.info("New reset-password link for e-mail address: " + email);
        UserDetails user = rootRepository.findUserByEmail(email);
        if (user != null) {
            String token = tokenService.issueToken(user.getUsername());
            String url = request.getScheme() + "://" + request.getServerName() + ":8080"; //TODO: aanpassen naar ext. server
                System.out.println(url);
            Mail msg = resetService.createResetMail(email, url, token);
            mailService.sendMail(msg);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Password reset link successfully sent to " + email);
        } else {
            logger.info("No account found for " + email);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No account has been found for " + email); //I_AM_A_TEAPOT :''''') ?
        }
    }

    @GetMapping("/reset")
    public ModelAndView loadResetPasswordPage(final ModelMap model, @RequestParam String token) { //ReqBody again?
        logger.info("New reset-password request via reset link");
        if (tokenService.verifyToken(token)) {
            model.addAttribute("token", token);
            //System.out.println("hij pakt dees sws");
            return new ModelAndView("redirect:/static/login/reset_password.html", model);
        }
        logger.info("User was null");
        //System.out.println("heya");
        //model.addAttribute("message", "Invalid token");
        return new ModelAndView("redirect:/index.html", model);
    }

    @PostMapping("/reset")
    public ResponseEntity<?> updateNewPassword(@RequestParam String password, @RequestParam String token) {
        logger.info("Update new password");
        String username = resetService.decodeTokenReturnUsername(token);
        UserDetails user = rootRepository.findUserByUsername(username);
        if (user != null && passwordValidationService.isValid(password)) {
            user.setPassword(password);
            rootRepository.updatePassword(user);
            return ResponseEntity.status(HttpStatus.OK).body("Password successfully updated");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid link or time has expired");
        }
    }
}

//https://www.codejava.net/frameworks/spring-boot/spring-security-forgot-password-tutorial
