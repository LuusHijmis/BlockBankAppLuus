package com.blockbank.service;

/**
 * @author hannahvd
 */

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordValidationServiceTest {

    PasswordValidationService passwordService = new PasswordValidationService();

    @Test
    void isPasswordIdentical() {
        assertFalse(passwordService.passwordNotIdentical("floEpid0eipiE.!", "floEpid0eipiE.!"));
        assertTrue(passwordService.passwordNotIdentical("floEpid0eipiE.!", "soepi3doePi!"));
    }

    @ParameterizedTest(name = "#{index} - Run test with password = {0}")
    @MethodSource("validPasswordProvider")
    void testValidPasswords(String password) {
        assertTrue(passwordService.isValid(password));
    }

    @ParameterizedTest(name = "#{index} - Run test with password = {0}")
    @MethodSource("invalidPasswordProvider")
    void testInvalidPasswords(String password) {
        assertFalse(passwordService.isValid(password));
    }

    static Stream<String> validPasswordProvider() {
        return Stream.of(
                "12345678901aB45678@1234",
                "UPPERCASECHARs.1",
                "lowercasecharS.1",
                " spaaaTi3ss. ",
                "_!heLLo0ooo!_#",
                "012345aBBa567.9");
    }

    static Stream<String> invalidPasswordProvider() {
        return Stream.of(
                "aBc1.",                        //invalid length 4; length must between 12-128
                "012345678901pPdS@!£234567sajkdhjskhgqelkhasdGGG89aasldkjaskf!£@$odiwjaskljfdaskjfdksahfdsvklhka" +
                        "slj987324987qjkwdhslakshhJfdlkasjd", //invalid length 128+; length must between 12-128
                "onlysmallcharacters.1",        //password must contain both upper and lower case
                "ONLYBIGCHARACTERS.1",          //password must contain both upper and lower case
                "012345678902",                 //password must contain a letter
                "!@£$%^&*()aBa",                //password must contain a digit
                "",                             //empty
                null);                          //empty
    }
}