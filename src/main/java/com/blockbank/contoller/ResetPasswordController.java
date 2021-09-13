package com.blockbank.contoller;

import com.blockbank.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hannahvd
 */

@RestController
public class ResetPasswordController {

    private final SendMailService service;

    @Autowired
    public ResetPasswordController(SendMailService service) {
        this.service = service;
    }




}
