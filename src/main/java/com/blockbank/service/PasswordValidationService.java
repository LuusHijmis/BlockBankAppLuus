package com.blockbank.service;

/**
 * @author hannahvd
 */

//TODO: populaire wachtwoorden check
//TODO: username check


import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PasswordValidationService {

        /*
    @Size(min = 12, max = 24)
    @NotNull
     */

    public static final String EMPTY_OR_NULL_PASSWORD = "Password can not be empty";
    public static final String ERROR_PASSWORD_LENGTH = "Password must be between 12 and 24 characters long."; //128 is a bit long? db-wise?
    public static final String ERROR_PASSWORD_CASE = "Password must include both upper and lowercase letters.";
    public static final String ERROR_LETTER_AND_DIGIT = "Password must contain both a letter and a digit between 0-9.";
    public static final String ERROR_SPECIAL_SYMBOL = "Password must contain a special symbol: @,#$%!^*().";

    //String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
    private final Pattern casePattern = Pattern.compile("[A-Z][a-z]"); //added [a-z]
    private final Pattern letterAndDigitPattern = Pattern.compile("(?=.*[a-z])(?=.*[0-9])");
    private final Pattern specialSymbolPattern = Pattern.compile("(.*[@,#$%!^*().].*$)");

    private boolean isValid;

    protected boolean isValid(String password) {
        if (checkEmpty(password)) {
            System.out.println(EMPTY_OR_NULL_PASSWORD);
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

    //not need to check(if)Empty because of min. length?
    private boolean checkEmpty(String password) {
        isValid = password == null || password.equals("");
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
        Matcher matcher = specialSymbolPattern.matcher(password);
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