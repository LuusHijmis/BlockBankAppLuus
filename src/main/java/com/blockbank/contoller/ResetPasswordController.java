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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author hannahvd
 */

@Controller
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

    @GetMapping("/forgot")
    public ResponseEntity<?> sendPasswordResetMail(@RequestParam("email") String userEmail, HttpServletRequest request) { //<String>
        logger.info("New reset password e-mail");
        UserDetails user = rootRepository.findUserByEmail(userEmail);
        if (user != null) {
            String token = tokenService.issueToken(user.getUsername());
                System.out.println("token CHECK");
            String url = request.getScheme() + "://" + request.getServerName();
                System.out.println("url CHECK");
            Mail msg = resetService.createResetMail(userEmail, url, token);
            mailService.sendMail(msg);
                System.out.println("mail sent CHECK");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Password reset link successfully sent to " + userEmail);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No account has been found for " + userEmail); //I_AM_A_TEAPOT :''''') ?
        }
    }

    @PostMapping("/reset")
    public ModelAndView loadResetPasswordPage(final ModelMap model, @RequestParam String token) {
        logger.info("New reset password link");
        if (tokenService.verifyToken(token)) {
            //String username = resetService.decodeTokenReturnUsername(token);
            model.addAttribute("token", token);
            return new ModelAndView("redirect:/resetPassword.html", model);
        }
        logger.info("User was null");
        //?
        return new ModelAndView("redirect:/index.html", model);
    }

    @PostMapping("/reset") //("/update")? //PutMapping?
    public ResponseEntity<?> updateNewPassword(@RequestParam String password, @RequestParam String email) { //hoe kom ik aan die email (zonder dat gebruiker invult?) //token?
        logger.info("Update new password");
        UserDetails userDetails = rootRepository.findUserByEmail(email);
        if (userDetails != null) {
            userDetails.setPassword(password);
            rootRepository.updatePassword(userDetails);
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
