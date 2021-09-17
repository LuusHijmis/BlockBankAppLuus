package com.blockbank.service;

/**
 * @author hannahvd
 * Serviceclass that checks new passwords created by user on given requirements.
 * Method isValid(String password) returns true only if every requirement is met.
 */

//TODO: check if input password1 matches password2 //done?
//TODO: popular passwords check
//TODO: fix regex into 1 regex to shorten method

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PasswordValidationService {

    private static final String EMPTY_OR_NULL_PASSWORD = "Password can not be empty.";
    private final String ERROR_LENGTH = String.format("Password must be between %d and %d characters long.", MIN_PASSWORD_LENGTH, MAX_PASSWORD_LENGTH);
    private static final String ERROR_CASE = "Password must include both upper and lowercase letters.";
    private static final String ERROR_LETTER_AND_DIGIT = "Password must contain both a letter and a digit between 0-9.";
    private static final String ERROR_SPECIAL_SYMBOL = "Password must contain a symbol: @,#$%!^*().";
    private static final int MIN_PASSWORD_LENGTH = 12;
    private static final int MAX_PASSWORD_LENGTH = 128;

    //String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
    private static final Pattern CASE_PATTERN = Pattern.compile("[A-Z][a-z]*");
    private static final Pattern LETTER_AND_DIGIT_PATTERN = Pattern.compile("(?=.*[a-z])(?=.*[0-9])");
    private static final Pattern SYMBOL_PATTERN = Pattern.compile("(.*[@,#$%!^*().].*$)");

    protected boolean passwordNotIdentical(String password1, String password2) {
        return !password1.equals(password2);
    }

    public boolean isValid(String password) {
        if (checkEmpty(password)) {
            throw new IllegalArgumentException(EMPTY_OR_NULL_PASSWORD);
        }
        if (checkIncorrectLength(password)) {
            throw new IllegalArgumentException(ERROR_LENGTH);
        }
        if (checkNoUpperCase(password)) {
            throw new IllegalArgumentException(ERROR_CASE);

        }
        if (checkNoLetterAndDigit(password)) {
            throw new IllegalArgumentException(ERROR_LETTER_AND_DIGIT);

        }
        if (checkNoSymbol(password)) {
            throw new IllegalArgumentException(ERROR_SPECIAL_SYMBOL);

        }
        return true;
    }

    private boolean checkEmpty(String password) {
        if (password == null || password.equals("")) {
            System.out.println(EMPTY_OR_NULL_PASSWORD);
            return true;
        }
        return false;
    }

    private boolean checkIncorrectLength(String password) {
        if (password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH) {
            System.out.println(ERROR_LENGTH);
            return true;
        }
        return false;
    }

    private boolean checkNoUpperCase(String password) {
        Matcher matcher = CASE_PATTERN.matcher(password);
        if (!matcher.find()) {
            System.out.println(ERROR_CASE);
            return true;
        }
        return false;
    }

    private boolean checkNoLetterAndDigit(String password) {
        Matcher matcher = LETTER_AND_DIGIT_PATTERN.matcher(password);
        if (!matcher.find()) {
            System.out.println(ERROR_LETTER_AND_DIGIT);
            return true;
        }
        return false;
    }

    private boolean checkNoSymbol(String password) {
        Matcher matcher = SYMBOL_PATTERN.matcher(password);
        if (!matcher.find()) {
            System.out.println(ERROR_SPECIAL_SYMBOL);
            return true;
        }
        return false;
    }

    //TODO: populaire wachtwoorden check
      //drie opties:

    //1. via API / extern ?
    private boolean checkPopularPasswordsExtern(String password) {
        return false;
    }

    //2. via resource File (import/update via extern?)
    private boolean checkPopularPasswordsFile(String password) {
        try {
            File passwordsFile = new File("passwordsFile.txt"); //file bestaat nog niet
            Scanner readPasswords = new Scanner(passwordsFile);
            while (readPasswords.hasNextLine()) {
                //bla
            }
        } catch (FileNotFoundException exception) {
            System.out.println("An error occurred.");
            exception.printStackTrace();
        }
        return false;
    }

    //3. via List (ain't the way imo)
    private boolean checkPopularPasswordsList(String password) {
        ArrayList<String> popularPasswords = new ArrayList<>();
        popularPasswords.add("Welkom12345.");
        popularPasswords.add("Welcome12345.");
        popularPasswords.add("Wachtwoord12345.");
        popularPasswords.add("Password12345.");
        for (int i = 0; i < popularPasswords.size(); i++) {
            if (popularPasswords.contains(password)) {
                System.out.println("Commonly used password.");
                return true;
            }
        }
        return false;
    }
}