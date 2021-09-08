package com.blockbank.service;

/**
 * @author Alex Shijan
 */

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
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
        Claim role = decodedJWT.getClaim("role");
        String expected = "{iss=\"Blockbank\", role=\"client\", username=\"Harold\"}";
       /* String expected = String.format("{role=\"client\", iss=\"Blockbank\", exp=%d, iat=%d, username=\"Harold\"}",
                tokenService.getExp().getTime(), tokenService.getIat().getTime());*/
        assertThat(actualClaims.toString()).isEqualTo(expected);
        System.out.println(role);
        System.out.println(actualClaims);
    }

    @Test
    void verifyTokenTest() {
        String token = tokenService.issueToken("Harold");
        assertThat(tokenService.verifyToken(token)).isEqualTo(true);
    }
}