package com.blockbank.service;

/**
 * @author Alex Shijan
 */


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@ActiveProfiles("test")
class LoginValidationServiceTest {

    @Autowired
    private LoginValidationService loginValidationService;

    @Autowired
    private HashService hashService;

    @Test
    void LoginValidationServiceNotNull() {
        assertThat(loginValidationService).isNotNull();}

    @Test
    void validateCorrectUsernamePassword() {
        String username = "Alex";
        String password = "xela";
        String hashedPassword = hashService.ultimateHash(password, "234");

        assertThat(loginValidationService.validate(username, password)).isEqualTo(true);
    }

    @Test
    void validateIncorrectPassword() {
        String username = "Alex";
        String password = "adadadads";
        String hashedPassword = hashService.ultimateHash(password, "234");

        assertThat(loginValidationService.validate(username, password)).isEqualTo(false);
    }

    @Test
    void validateIncorrectUsername() {
        String username = "frwfwrfwrf";
        String password = "adadadads";
        String hashedPassword = hashService.ultimateHash(password, "234");

        assertThat(loginValidationService.validate(username, password)).isEqualTo(false);

    }
}