package com.blockbank.service;

/**
 * @author hannahvd
 */

//TODO: populaire wachtwoorden check
//https://stackoverflow.com/questions/46116139/password-validation-service
//TODO: username check

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    //String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
    private final Pattern casePattern = Pattern.compile("[A-Z][a-z]"); //added [a-z]
    private final Pattern letterAndDigitPattern = Pattern.compile("(?=.*[a-z])(?=.*[0-9])");
    private final Pattern symbolPattern = Pattern.compile("(.*[@,#$%!^*().].*$)");

    private boolean isValid = true;

    //isValid = true;
        //else return listOfFailures;
        //return false;
    //return isValid;

    protected boolean isValid(String password) {
        List<String> failures = new ArrayList<>();
        if (checkEmpty(password, failures)) {
            return false;
        }
        if (checkLength(password)) {
            System.out.println(ERROR_PASSWORD_LENGTH);
            return false;
        }
        if (checkCase(password)) {
            System.out.println(ERROR_PASSWORD_CASE);
            return false;
        }
        if (checkLetterAndDigit(password)) {
            System.out.println(ERROR_LETTER_AND_DIGIT);
            return false;
        }
        if (checkSymbol(password)) {
            System.out.println(ERROR_SPECIAL_SYMBOL);
            return false;
        }
        return isValid;
    }

    //isValid = true;
        //else return listOfFailures;
        //return false;
    //return isValid;

    protected boolean isValiid(String password) {
        List<String> failures = new ArrayList<>();



        /*
        if (checkEmpty(password, failures)) {
            return false;
        }
        if (checkLength(password)) {
            System.out.println(ERROR_PASSWORD_LENGTH);
            return false;
        }
        if (checkCase(password)) {
            System.out.println(ERROR_PASSWORD_CASE);
            return false;
        }
        if (checkLetterAndDigit(password)) {
            System.out.println(ERROR_LETTER_AND_DIGIT);
            return false;
        }
        if (checkSymbol(password)) {
            System.out.println(ERROR_SPECIAL_SYMBOL);
            return false;
        }
        */
        return isValid;
    }



    //not need to check(if)Empty because of min. length?
    private boolean checkEmpty(String password, List<String> failures) {
        isValid = password == null || password.equals("");
        failures.add(EMPTY_OR_NULL_PASSWORD);
        return isValid;
    }

    private boolean checkLength(String password) {
        isValid = password.length() < 12 || password.length() > 24;
        return isValid;
    }

    private boolean checkCase(String password) {
        isValid = false;
        Matcher matcher = casePattern.matcher(password);
        if (!matcher.find()) {
            isValid = true;
        }
        return isValid;
    }

    private boolean checkLetterAndDigit(String password) {
        isValid = false;
        Matcher matcher = letterAndDigitPattern.matcher(password);
        if (!matcher.find()) {
            isValid = true;
        }
        return isValid;
    }

    private boolean checkSymbol(String password) {
        isValid = false;
        Matcher matcher = symbolPattern.matcher(password);
        if (!matcher.find()) {
            isValid = true;
        }
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