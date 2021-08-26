package com.blockbank.service;

/**
 * @author hannahvd
 */

import com.blockbank.database.repository.JdbcUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UsernameValidationService {

    private JdbcTemplate jdbcTemplate;
    private final JdbcUserDao jdbcUserDao = new JdbcUserDao(null); // ?
    private final Logger logger = LoggerFactory.getLogger(UsernameValidationService.class);

    private static final int MIN_USERNAME_LENGTH = 3; //discutabel
    private static final int MAX_USERNAME_LENGTH = 27; //discutabel

    private final Pattern pattern = Pattern.compile("[A-Za-z0-9_]+");

    /*
    - not identical to e-mail ?
    - length ?
    - toLowerCase / niet hoofdlettergevoelig

    - logger vs sout?
    - jdbc wat
    */

    private boolean checkEmpty(String username) {
        if (username == null || username.equals("")) {
            logger.info("Username can not be empty.");
            return true;
        }
        return false;
    }

    private boolean checkNotUnique(String username) {
        return jdbcUserDao.findByUsername(username) != null;
    }

    private boolean checkIncorrectLength(String username) {
        if (username.length() < MIN_USERNAME_LENGTH || username.length() > MAX_USERNAME_LENGTH) {
            logger.info("Username should be between " + MIN_USERNAME_LENGTH + "-" + MAX_USERNAME_LENGTH + " characters long.");
        }
        return false;
    }

    private boolean checkSymbolsAccents(String username) {
        Matcher matcher = pattern.matcher(username);
        if (matcher.find()) {
            return false;
        }
        logger.info("Username can not contain any symbols or accents");
        return true;
    }
}
