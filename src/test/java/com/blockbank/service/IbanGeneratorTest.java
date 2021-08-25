package com.blockbank.service;

import org.junit.jupiter.api.Test;

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
        String iban = ibanGenerator.generateSimpleIban();
        System.out.println(iban);
    }
}