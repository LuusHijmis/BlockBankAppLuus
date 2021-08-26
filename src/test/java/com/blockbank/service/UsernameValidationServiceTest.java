package com.blockbank.service;

/**
 * @author hannahvd
 */

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UsernameValidationServiceTest {

    UsernameValidationService usernameValidationService = new UsernameValidationService();

    @Test   //TODO: testUniqueUsername() fails because "this.rootRepository" is null (???)
    void testUniqueUsername() {
        assertFalse(usernameValidationService.isValid("Harold"));
        assertFalse(usernameValidationService.isValid("Hannah"));
    }

    @ParameterizedTest(name = "#{index} - Run test with username = {0}")
    @MethodSource("validUsernameProvider")
    void testValidUsernames(String username) {
        assertTrue(usernameValidationService.isValid(username));
        //to run this test you need to comment-off the boolean-check 'checkNotUnique(String username)'
         //in the isValid() method in UsernameValidationService.class
    }

    @ParameterizedTest(name = "#{index} - Run test with username = {0}")
    @MethodSource("invalidUsernameProvider")
    void testInvalidUsernames(String username) {
        assertFalse(usernameValidationService.isValid(username));
        //to run this test you need to comment-off the boolean-check 'checkNotUnique(String username)'
         //in the isValid() method in UsernameValidationService.class
    }

    static Stream<String> validUsernameProvider() {
        return Stream.of(
                "mkyong",
                "javaregex",
                "JAVAregex",
                "java.regex",
                "java-regex",
                "java_regex",
                "java.regex.123",
                "java-regex-123",
                "java_regex_123",
                "javaregex123",
                "123456",
                "java123",
                "01234567890123456789");
    }

    static Stream<String> invalidUsernameProvider() {
        return Stream.of(
                "abc",                      // invalid length 3; length must between 5-20
                "01234567890123456789a",    // invalid length 21; length must between 5-20
                "_javaregex_",              // invalid start and last character
                ".javaregex.",              // invalid start and last character
                "-javaregex-",              // invalid start and last character
                "javaregex#$%@123",         // invalid symbols, support dot, hyphen and underscore
                "java..regex",              // dot cant appear consecutively
                "java--regex",              // hyphen can't appear consecutively
                "java__regex",              // underscore can't appear consecutively
                "java._regex",              // dot and underscore can't appear consecutively
                "java.-regex",              // dot and hyphen can't appear consecutively
                " ",                        // empty
                "");                        // empty
    }
}