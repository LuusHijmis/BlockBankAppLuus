package com.blockbank.service;

/**
 * @author hannahvd
 */

//TODO: populaire wachtwoorden check via extern
//https://stackoverflow.com/questions/46116139/password-validation-service
//TODO: username check
/*
    - unique
    - not identical to e-mail*?
    - min. + max. characters to username?
    - wel cijfers
    - niet symbolen
    - toLowerCase / niet hoofdlettergevoelig
    - é ö ø
 */

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PasswordValidationService {

     /*
    @Size(min = 12, max = 24)
    @NotNull
     */

    private static final String EMPTY_OR_NULL_PASSWORD = "Password can not be empty";
    private static final String ERROR_PASSWORD_LENGTH = "Password must be between 12 and 24 characters long."; //128 is a bit long? db-wise?
    private static final String ERROR_PASSWORD_CASE = "Password must include both upper and lowercase letters.";
    private static final String ERROR_LETTER_AND_DIGIT = "Password must contain both a letter and a digit between 0-9.";
    private static final String ERROR_SPECIAL_SYMBOL = "Password must contain a symbol: @,#$%!^*().";
    private static final int MIN_PASSWORD_LENGTH = 12;
    private static final int MAX_PASSWORD_LENGTH = 24;

    //String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
    private final Pattern casePattern = Pattern.compile("[A-Z][a-z]");
    private final Pattern letterAndDigitPattern = Pattern.compile("(?=.*[a-z])(?=.*[0-9])");
    private final Pattern symbolPattern = Pattern.compile("(.*[@,#$%!^*().].*$)");

    private boolean isValid = true;

    protected boolean isValid(String password) {
        if (checkEmpty(password)) {
            return false;
        }
        if (checkLength(password)) {
            return false;
        }
        if (checkCase(password)) {
            return false;
        }
        if (checkLetterAndDigit(password)) {
            return false;
        }
        if (checkSymbol(password)) {
            return false;
        }
        return isValid;
    }

    private boolean checkEmpty(String password) {
        isValid = false;
        if (password == null || password.equals("")) {
            System.out.println(EMPTY_OR_NULL_PASSWORD);
            isValid = true;
        }
        return isValid;
    }

    private boolean checkLength(String password) {
        isValid = false;
        if (password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH) {
            System.out.println(ERROR_PASSWORD_LENGTH);
            isValid = true;
        }
        return isValid;
    }

    private boolean checkCase(String password) {
        isValid = false;
        Matcher matcher = casePattern.matcher(password);
        if (!matcher.find()) {
            System.out.println(ERROR_PASSWORD_CASE);
            isValid = true;
        }
        return isValid;
    }

    private boolean checkLetterAndDigit(String password) {
        isValid = false;
        Matcher matcher = letterAndDigitPattern.matcher(password);
        if (!matcher.find()) {
            System.out.println(ERROR_LETTER_AND_DIGIT);
            isValid = true;
        }
        return isValid;
    }

    private boolean checkSymbol(String password) {
        isValid = false;
        Matcher matcher = symbolPattern.matcher(password);
        if (!matcher.find()) {
            System.out.println(ERROR_SPECIAL_SYMBOL);
            isValid = true;
        }
        return isValid;
    }

    //via List
    private boolean checkPopularPasswords(String password) {
        isValid = false;

        return isValid;
    }

    //via resource file
    private boolean checkPopularPws(String password) {
        isValid = false;

        return isValid;
    }
}


/*

meest gebruikte wachtwoorden

123456.
qwerty.
123456789.
welkom.
12345.
password.
welkom01.
wachtwoord.

 */