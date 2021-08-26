package com.blockbank.service;

/**
 * @author hannahvd
 */
    /*
    - not identical to e-mail ?
    - toLowerCase / niet hoofdlettergevoelig ; vanuit waar aanpassen?
    - password char[] = more safe than using String;
    */

import com.blockbank.database.repository.RootRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UsernameValidationService {

    private static final String ERROR_EMPTY_NULL_USERNAME = "Username can not be empty.";
    private static final String ERROR_UNIQUE_USERNAME = "Username already exists in database.";
    private final String ERROR_LENGTH_USERNAME = String.format("Username should be between " + MIN_USERNAME_LENGTH + "-" + MAX_USERNAME_LENGTH + " characters long.");
    private static final String ERROR_SYMBOLS_AND_ACCENTS = "Username can not contain any symbols or accents.";
    private static final int MIN_USERNAME_LENGTH = 6; //discutabel
    private static final int MAX_USERNAME_LENGTH = 30; //discutabel

    private final Pattern pattern = Pattern.compile("[A-Za-z0-9_]+");
    private RootRepository rootRepository;
    // [a-zA-Z0-9.]+@){0,1}([a-zA-Z0-9.]    // check op spaties !!!

    protected boolean isValid(String username) {
        if (checkEmpty(username)) {
            return false;
        }
        if (checkNotUnique(username)) {
            return false;
        }
        if (checkIncorrectLength(username)) {
            return false;
        }
        if (checkSymbolsAccents(username)) {
            return false;
        }
        return true;
    }

    private boolean checkEmpty(String username) {
        if (username == null || username.equals("")) {
            System.out.println(ERROR_EMPTY_NULL_USERNAME);
            return true;
        }
        return false;
    }

    private boolean checkNotUnique(String username) {
        if (rootRepository.findUserByUsername(username) != null) {
            System.out.println(ERROR_UNIQUE_USERNAME);
            return true;
        }
        return false;
    }

    private boolean checkIncorrectLength(String username) {
        if (username.length() < MIN_USERNAME_LENGTH || username.length() > MAX_USERNAME_LENGTH) {
            System.out.println(ERROR_LENGTH_USERNAME);
        }
        return false;
    }

    private boolean checkSymbolsAccents(String username) {
        Matcher matcher = pattern.matcher(username);
        if (matcher.find()) {
            return false;
        }
        System.out.println(ERROR_SYMBOLS_AND_ACCENTS);
        return true;
    }
}
