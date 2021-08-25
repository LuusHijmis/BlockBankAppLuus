package com.blockbank.service;

/**
 * @author Alex Shijan
 */

import java.util.concurrent.ThreadLocalRandom;

public class IbanGenerator {
    private final String LAND_CODE = "NL";
    private final String BANK_CODE = "BLOK";
    private final int ACCOUNT_NUMBER_MAX_LENGTH = 10;
    private final int ACCOUNT_NUMBER_MAX_VALUE = 999999999;
    private final int ACCOUNT_NUMBER_MIN_VALUE = 1;
    private final int CONTROL_DIGIT_MIN_VALUE = 10;
    private final int MOD_97 = 97;
    private final int BIG_INT = 98;

    public String generateSimpleIban() {
        String accountNumber = generateAccountNumber();
        int controlNumber = ThreadLocalRandom.current().nextInt(10,
                99 + 1);
        String iban = String.format(LAND_CODE+controlNumber+BANK_CODE+accountNumber);
        return iban;
    }

    public String generateIban() {
        String iban;
        String accountNumber = generateAccountNumber();
        String controlNumber;
        String numericAccountNumber;
        String calc;
        long controlDigits;

        calc = addBankCode(accountNumber);
        calc = addCountryCode(calc);
        numericAccountNumber = convertToNumeric(calc);
        numericAccountNumber = addTwoZeroes(numericAccountNumber);
        controlDigits = Long.parseLong(numericAccountNumber)%MOD_97;
        controlDigits = BIG_INT - controlDigits;
        controlNumber = checkDigitResult(controlDigits);
        iban = construcIban(controlNumber,accountNumber);
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

}
