package com.blockbank.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsernameValidationServiceTest {

    UsernameValidationService usernameValidationService = new UsernameValidationService();

    @Test
    void isValid() {
        String username = "hi";
        usernameValidationService.isValid(username);
    }

    void checkEmpty() {
        usernameValidationService.isValid(null);
    }

    void checkUnique() {
        usernameValidationService.isValid("<BestaandeUsername>");
    }

    void checkLength() {
        usernameValidationService.isValid("i");
    }

    void checkSymbols() {

    }

}