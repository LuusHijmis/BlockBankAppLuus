package com.blockbank.service;

/**
 * @author Alex Shijan
 */

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IbanGeneratorTest {

    IbanGenerator ibanGenerator = new IbanGenerator();

    @Test
    public void RandomGenerationTest() {
        //Arrange
        int expectedLength = 10;
        //Act
        String account = ibanGenerator.generateAccountNumber();
        System.out.println(account);
        //Assert
        assertThat(account).containsOnlyDigits();
        assertThat(account.length()).isEqualTo(expectedLength);
    }

    @Test
    public void generateIbanTest() {
        //Arrange
        String landCode = "NL";
        String bankCode = "BLOK";
        int expectedLength = 18;
        BigInteger IBANNUMBER_MAGIC_NUMBER = new BigInteger("97");

        //Act
        String iban = ibanGenerator.generateIban();
        String account = ibanGenerator.getAccountNumber();
        String controlNumber = ibanGenerator.getControlNumber();
        System.out.println(account);
        System.out.println(controlNumber);
        System.out.println(iban);

        assertThat(iban).startsWith(landCode).contains(bankCode);
        assertThat(iban.length()).isEqualTo(expectedLength);

        // Check if the generated IBAN is a valid IBAN number.
        // Move the four initial characters to the end of the string.
        String newIban = iban.substring(4) + iban.substring(0, 4);

        // Replace each letter in the string with two digits, thereby expanding the string, where A = 10, B = 11, ..., Z = 35.
        StringBuilder numericAccountNumber = new StringBuilder();
        for (int i = 0;i < newIban.length();i++) {
            numericAccountNumber.append(Character.getNumericValue(newIban.charAt(i)));
        }

        // Interpret the string as a decimal integer and compute the remainder of that number on division by 97.
        BigInteger ibanNumber = new BigInteger(numericAccountNumber.toString());
        assertThat(ibanNumber.mod(IBANNUMBER_MAGIC_NUMBER).intValue()).isEqualTo(1);
    }

}