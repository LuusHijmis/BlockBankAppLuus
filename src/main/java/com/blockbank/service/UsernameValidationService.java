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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UsernameValidationService {

    private final RootRepository rootRepository;
    private final Logger logger = LoggerFactory.getLogger(UsernameValidationService.class);

    private static final String ERROR_UNIQUE_USERNAME = "Username already exists in database.";
    private static final String ERROR_EMPTY_USERNAME = "Username can not be empty.";
    private static final String ERROR_USERNAME =
            "Username consists of alphanumeric characters (a-zA-Z0-9), lowercase, or uppercase.\n" +
            "Username allowed of the dot (.), underscore (_), and hyphen (-).\n" +
            "The dot (.), underscore (_), or hyphen (-) must not be the first or last character.\n" +
            "The dot (.), underscore (_), or hyphen (-) does not appear consecutively, e.g., java..regex\n" +
            "The number of characters must be between 5 to 20.\n";
    private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$");

    @Autowired
    public UsernameValidationService(RootRepository rootRepository) {
        super();
        this.rootRepository = rootRepository;
        logger.info("New UsernameValidationService");
    }

    public boolean isValid(String username) {
        if (checkNotUnique(username)) {
            return false;
        }
        if (checkEmpty(username)) {
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

    private boolean checkEmpty(String username) {
        if (username == null || username.equals("")) {
            System.out.println(ERROR_EMPTY_USERNAME);
            return true;
        }
        return false;
    }

    private boolean checkSymbolsAccents(String username) {
        Matcher matcher = PATTERN.matcher(username);
        if (matcher.find()) {
            return false;
        }
        System.out.println(ERROR_USERNAME);
        return true;
    }
}
