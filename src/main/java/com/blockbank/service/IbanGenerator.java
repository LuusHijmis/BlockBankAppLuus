package com.blockbank.service;

/**
 * @author Alex Shijan
 */

import com.blockbank.database.repository.JdbcAccountDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class IbanGenerator {

    private final Logger logger = LoggerFactory.getLogger(IbanGenerator.class);

    private final String LAND_CODE = "NL";
    private final String BANK_CODE = "BLOK";
    private final int ACCOUNT_NUMBER_MAX_LENGTH = 10;
    private final int ACCOUNT_NUMBER_MAX_VALUE = 999999999;
    private final int ACCOUNT_NUMBER_MIN_VALUE = 1;
    private final int CONTROL_DIGIT_MIN_VALUE = 10;
    private final int MOD_97 = 97;
    private final int BIG_INT = 98;
    private String accountNumber;
    private String controlNumber;

    @Autowired
    public IbanGenerator() {
    }

    public String generateIban() {
        String iban;
        accountNumber = generateAccountNumber();
        String numericAccountNumber;
        String calc;
        BigInteger bigInteger;
        long controlDigits;

        calc = addBankCode(accountNumber);
        calc = addCountryCode(calc);
        numericAccountNumber = convertToNumeric(calc);
        numericAccountNumber = addTwoZeroes(numericAccountNumber);
        bigInteger = new BigInteger(numericAccountNumber);
        bigInteger = bigInteger.remainder(BigInteger.valueOf(MOD_97));
        bigInteger = BigInteger.valueOf(BIG_INT).subtract(bigInteger);
        controlDigits = bigInteger.longValue();
        controlNumber = checkDigitResult(controlDigits);
        iban = construcIban(controlNumber,accountNumber);
        logger.info("New IBAN generated.");
        return iban;
    }

    public String generateAccountNumber() {
       int startingValue = ThreadLocalRandom.current().nextInt(ACCOUNT_NUMBER_MIN_VALUE,
                ACCOUNT_NUMBER_MAX_VALUE + 1);
       String startingLength = String.valueOf(startingValue);
       if (startingLength.length() < ACCOUNT_NUMBER_MAX_LENGTH) {
           int toAdd = ACCOUNT_NUMBER_MAX_LENGTH - startingLength.length();
           for (int i = 0; i < toAdd; i++) {
               startingLength = String.format("0"+startingLength);
           }
       }
       return startingLength;
    }

    public String addBankCode(String account) {
        String result = String.format(BANK_CODE+account);
        return result;
    }

    public String addCountryCode(String account) {
        String result = String.format(account+LAND_CODE);
        return result;
    }

    public String addTwoZeroes(String account) {
        String result = String.format(account+"00");
        return result;
    }

    public String checkDigitResult(long digit) {
        if(digit < CONTROL_DIGIT_MIN_VALUE) {
            return String.format("0"+digit);
        } else {
            return String.valueOf(digit);
        }
    }

    public String construcIban(String controlNumber, String accountNumber) {
        return String.format(LAND_CODE+controlNumber+BANK_CODE+accountNumber);
    }

    // Replace each letter in the string with two digits, thereby expanding the string, where A = 10, B = 11, ..., Z = 35.
    public String convertToNumeric(String account) {
        StringBuilder numericAccountNumber = new StringBuilder();
        for (int i = 0;i < account.length();i++) {
            numericAccountNumber.append(Character.getNumericValue(account.charAt(i)));
        }
        return numericAccountNumber.toString();
    }

    public String getLAND_CODE() {
        return LAND_CODE;
    }

    public String getBANK_CODE() {
        return BANK_CODE;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getControlNumber() {
        return controlNumber;
    }
}
