package com.blockbank.database.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserDetailsTest {

    @Test
    public void constructionValidArguementsCreatesClient() {
        // Arrange - niet nodig in dit geval.
        Address adress = new Address("koningininenstraat", 777, "D",
                "1234ab", "amsterdam", "NL");
        ClientDetails clientDetails = new ClientDetails("Lucien", "", "H", LocalDate.parse("1990-12-05"), 89237, "email@email.com");
        // Act.
        var instance = new UserDetails("Alex", "123", "salt", clientDetails, adress);
        System.out.println(instance);

        // Assert.
        assertNotNull(instance);
        assertThat(instance.getRole()).isEqualTo("client");
    }

     @Test
    public void constructionValidArguementsCreatesAdministrator() {
        // Arrange - niet nodig in dit geval.

        // Act.
        var instance = new UserDetails("Alex", "123", "salt");
        System.out.println(instance);

        // Assert.
        assertNotNull(instance);
        assertThat(instance.getRole()).isEqualTo("administrator");
    }

}