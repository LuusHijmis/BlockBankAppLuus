package com.blockbank.service;

/**
 * @author Alex Shijan
 */

import com.blockbank.database.domain.Address;
import com.blockbank.database.domain.ClientDetails;
import com.blockbank.database.domain.UserDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
class TokenServiceTest {

    @Autowired
    TokenService tokenService;

    @Test
    void issueToken() {
        ClientDetails clientDetails = new ClientDetails("Harold", "","Stevens",
                LocalDate.parse("1973-09-25"),123456007,"info@hjstevens.nl");
        Address address = new Address("Pieter Woutersstraat",26,null,"2215MC",
                "Voorhout","");
        UserDetails tokenUser = new UserDetails("Harold","dlorah","123",clientDetails,address);
        tokenUser.setUserID(1);

        String expected = String.format(
                "{\"iss\": \"%s\",\"iat\": %d,\"exp\": %d,\"aud\": \"%s\",\"sub\": \"%s\",\"Role\": \"%s\"}"
                , "Blockbank", 1630497103, 1630498301, "www.blockbank.com", tokenUser.getUsername(),
                tokenUser.getRole());
        String actual = tokenService.issueToken("Harold");
        System.out.println(actual);
    }
}