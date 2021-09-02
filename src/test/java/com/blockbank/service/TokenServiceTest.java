package com.blockbank.service;

/**
 * @author Alex Shijan
 */

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blockbank.database.domain.Address;
import com.blockbank.database.domain.ClientDetails;
import com.blockbank.database.domain.UserDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class TokenServiceTest {

    @Autowired
    TokenService tokenService;

    @Test
    void issueTokenWithCorrectClaims() {
        String token = tokenService.issueToken("Harold");
        DecodedJWT decodedJWT = JWT.decode(token);

        Map<String, Claim> actualClaims = decodedJWT.getClaims();
        String expected = "{iss=\"Blockbank\", role=\"client\", username=\"Harold\"}";

        assertThat(actualClaims.toString()).isEqualTo(expected);
    }

    @Test
    void verifyTokenTest() {
        String token = tokenService.issueToken("Harold");

        assertThat(tokenService.verifyToken(token)).isEqualTo(true);
    }
}