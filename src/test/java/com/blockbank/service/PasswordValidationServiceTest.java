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
                "12345678901aB45678@1234");
//                "mkyong",
//                "javaregex",
//                "JAVAregex",
//                "java.regex",
//                "java-regex",
//                "java_regex",
//                "java.regex.123",
//                "java-regex-123",
//                "java_regex_123",
//                "javaregex123",
//                "123456",
//                "java123",
//                "01234567890123456789");
    }

    static Stream<String> invalidPasswordProvider() {
        return Stream.of(
                "aBc1.",                        //invalid length 3; length must between 12-24
                "012345678901pPdS@!£23456789a", //invalid length 21; length must between 12-24
                "_javaregex_",                  //invalid start and last character
                ".javaregex.",                  //invalid start and last character
                "-javaregex-",                  //invalid start and last character
                "javaregex#$%@123",             //invalid symbols, support dot, hyphen and underscore
                "java..regex",                  //dot cant appear consecutively
                "java--regex",                  //hyphen can't appear consecutively
                "java__regex",                  //underscore can't appear consecutively
                "java._regex",                  //dot and underscore can't appear consecutively
                "java.-regex",                   //dot and hyphen can't appear consecutively
                " ",                            //empty
                "");                            //empty
    }
}