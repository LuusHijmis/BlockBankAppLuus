package com.blockbank.service;

/*@author Karish Resodikromo
* Deze methodes testen de ClientRegistrationService klasse*/

import com.blockbank.database.domain.Address;
import com.blockbank.database.domain.ClientDetails;
import com.blockbank.database.domain.RegistrationDTO;
import com.blockbank.database.domain.UserDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClientRegistrationServiceTest {


    @Autowired
    private ClientRegistrationService clientRegistrationService;


    @Test
    public void clientRegistrationServiceNotNull() {
        assertThat(clientRegistrationService).isNotNull();
    }


    @Test
    public void registerClientTest() {
        UserDetails testUserDetails = new UserDetails("hannah", "Hoofdletter1!", "123",
                new ClientDetails("Hannah", "van", "Dam", LocalDate.parse("1999-01-07"),
                        123477759, "hannah@test.nl"),
                new Address("Prinsessenstraat", 29, "", "1300PT", "Rotterdam,",
                        "Nederland"));
        testUserDetails.setRole("client");
        RegistrationDTO registrationDTO = new RegistrationDTO("Hannah", "van", "Dam",
                LocalDate.parse("1999-01-07"), 123477759, "hannah@test.nl", "hannah",
                "Hoofdletter1!",
                "Prinsessenstraat", 29, "", "1300PT", "Rotterdam",
                "Nederland");
        UserDetails Hannah = clientRegistrationService.register(registrationDTO);
        assertThat(Hannah.getRole()).isEqualTo("client");
        assertThat(Hannah.getUsername()).isEqualTo("hannah");
    }

}