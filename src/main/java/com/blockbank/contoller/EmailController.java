package com.blockbank.contoller;

import com.blockbank.database.domain.Mail;
import com.blockbank.service.SendMailService;
import org.apache.http.MessageConstraintException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

/**
 * @author hannahvd
 */

@RestController
@RequestMapping("/api/vl/mail") //nog ff naar kieken
public class EmailController {

    SendMailService service;

    @Autowired
    public EmailController(SendMailService service) {
        this.service = service;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMail (@RequestBody Mail mail) {
        service.sendMail(mail);
        return new ResponseEntity<>("E-mail sent", HttpStatus.OK);
    } //return (new) ModelAndView

    @PostMapping("/attachtment")
    public ResponseEntity<String> sendAttachmentEmail(@RequestBody Mail mail) throws MessagingException, MessageConstraintException {
        service.sendMailWithAttachments(mail);
        return new ResponseEntity<>("Attachment e-mail sent", HttpStatus.OK);
    }
}
