package com.blockbank.service;

/**
 * @author hannahvd
 */

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PasswordValidationService {

    public static final String EMPTY_OR_NULL_PASSWORD = "Password can not be empty";
    public static final String ERROR_PASSWORD_LENGTH = "Password must be between 12 and 24 characters long."; //128 is a bit long? db-wise?
    public static final String ERROR_PASSWORD_CASE = "Password must include both upper and lowercase letters.";
    public static final String ERROR_LETTER_AND_DIGIT = "Password must contain both a letter and a digit between 0-9.";
    public static final String ERROR_SPECIAL_SYMBOL = "Password must contain a special symbol: @,#$%!^*().";

    /*
    @Size(min = 12, max = 24)
    @NotNull
     */

    //String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
    private final Pattern casePattern = Pattern.compile("[A-Z][a-z]"); //added [a-z]
    private final Pattern letterAndDigitPattern = Pattern.compile("(?=.*[a-z])(?=.*[0-9])");
    private final Pattern specialSymbolPattern = Pattern.compile("(.*[@,#$%!^*().].*$)");

    private boolean isValid;

    protected boolean isValid(String password) {
        isValid = true;
        isValid = !checkEmpty(password);
        isValid = !checkLength(password);
        isValid = !checkCase(password);
        isValid = !checkLetterAndDigit(password);
        isValid = !checkSymbol(password);
        return isValid;
    }
/*
    protected boolean isValiid(String password) {
        checkEmpty(password);
        checkLength(password);
        checkCase(password);
        checkLetterAndDigit(password);
        checkSymbol(password);
        return isValid;
    }
*/
    //not need to check because of min. length?
    private boolean checkEmpty(String password) {
        isValid = true;
        if (password == null || password.equals("")) {
            System.out.println(EMPTY_OR_NULL_PASSWORD);
            isValid = false;
        }
        return isValid;
    }

    private boolean checkLength(String password) {
        isValid = true;
        if (password.length() < 12 || password.length() > 24){
            System.out.println(ERROR_PASSWORD_LENGTH);
            isValid = false;
        }
        return isValid;
    }

    private boolean checkCase(String password) {
        isValid = true;
        Matcher matcher = casePattern.matcher(password);
        if (matcher.find()) {
            System.out.println(ERROR_PASSWORD_CASE);
            isValid = false;
        }
        return isValid;
    }

    private boolean checkLetterAndDigit(String password) {
        isValid = true;
        Matcher matcher = letterAndDigitPattern.matcher(password);
        if (matcher.find()) {
            System.out.println(ERROR_LETTER_AND_DIGIT);
            isValid = false;
        }
        return isValid;
    }

    private boolean checkSymbol(String password) {
        isValid = true;
        Matcher matcher = specialSymbolPattern.matcher(password);
        if (matcher.find()) {
            System.out.println(ERROR_SPECIAL_SYMBOL);
            isValid = false;
        }
        return isValid;
    }

    //@Autowired
    //private Validator validator;


}

/*

    public static boolean isValidPassword(String password) {
        boolean isValid = true;
        if (password.length() > 15 || password.length() < 8)
        {
            System.out.println("Password must be less than 20 and more than 8 characters in length.");
            isValid = false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars ))
        {
            System.out.println("Password must have atleast one uppercase character");
            isValid = false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars ))
        {
            System.out.println("Password must have atleast one lowercase character");
            isValid = false;
        }
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers ))
        {
            System.out.println("Password must have atleast one number");
            isValid = false;
        }
        String specialChars = "(.*[@,#,$,%].*$)";
        if (!password.matches(specialChars ))
        {
            System.out.println("Password must have atleast one special character among @#$%");
            isValid = false;
        }
        return isValid;
    }

*/