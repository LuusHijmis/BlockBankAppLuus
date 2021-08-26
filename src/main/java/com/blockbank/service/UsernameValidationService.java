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

    private RootRepository rootRepository;

    private static final String ERROR_UNIQUE_USERNAME = "Username already exists in database.";
    private static final String ERROR_USERNAME =
            "Username consists of alphanumeric characters (a-zA-Z0-9), lowercase, or uppercase.\n" +
            "Username allowed of the dot (.), underscore (_), and hyphen (-).\n" +
            "The dot (.), underscore (_), or hyphen (-) must not be the first or last character.\n" +
            "The dot (.), underscore (_), or hyphen (-) does not appear consecutively, e.g., java..regex\n" +
            "The number of characters must be between 5 to 20.";
    private static final Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$");

    protected boolean isValid(String username) {
        if (checkNotUnique(username)) {
            return false;
        }
        if (checkSymbolsAccents(username)) {
            return false;
        }
        return true;
    }

    private boolean checkNotUnique(String username) {
        if (rootRepository.findUserByUsername(username) != null) {
            System.out.println(ERROR_UNIQUE_USERNAME);
            return true;
        }
        return false;
    }

    private boolean checkSymbolsAccents(String username) {
        Matcher matcher = usernamePattern.matcher(username);
        if (matcher.find()) {
            return false;
        }
        System.out.println(ERROR_USERNAME);
        return true;
    }
}
