package com.blockbank.database.domain;

/**
 * @author Alex Shijan
 *Unit testsvoor Account Model.
 * Tests zijn geimplementeerd metArrange-Act-Assertpattern.
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    public void constructionValidArguementsCreatesInstance() {
        // Arrange - niet nodig in dit geval.
        // Act.
        var instance = new Account(1000, 10);
        instance.setIban("NLkk BBBB 9999 9999 99");

        // Assert.
        assertNotNull(instance);
    }

    @Test
    public void constructionNegativeArguementThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            var instance = new Account(-1000, 10);
            instance.setIban("NLkk BBBB 9999 9999 99");
        });
    }

    @Test
    void testToString() {
        //Arrange - niet nodig in dit geval.

        // Act.
        var instance = new Account(1000, 10);
        instance.setIban("NLkk BBBB 9999 9999 99");
        String output = instance.toString();
        System.out.println(output);

        //Assert.
        assertEquals("Account with iban NLkk BBBB 9999 9999 99 belongs to client with id 10. Balamce: 1000.00."
                , output);

    }
}