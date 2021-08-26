package com.blockbank.service;

/**
 * @author hannahvd
 */

//TODO: goede tests schrijven

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PasswordValidationServiceTest {

    PasswordValidationService passwordService = new PasswordValidationService();

    @Test
    void isValid() {
        String password = "zonderupperCase1@";
        assert passwordService.isValid(password);

        //assertThat("");

    }
}