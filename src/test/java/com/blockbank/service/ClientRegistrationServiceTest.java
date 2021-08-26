package com.blockbank.service;

import com.blockbank.database.domain.UserDTO;
import com.blockbank.database.domain.UserDetails;
import com.blockbank.database.repository.RootRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClientRegistrationServiceTest {

    private RootRepository mockRepo;
    private HashService mockHash = new HashService();
    private PasswordValidationService mockPass = new PasswordValidationService();
    private SaltGenerator mockSalt = new SaltGenerator();
    private IbanGenerator mockIban = new IbanGenerator();
    private ClientRegistrationService clientRegistrationService;

    @BeforeAll
    public void setUP() {
        mockRepo = Mockito.mock(RootRepository.class);
        clientRegistrationService = new ClientRegistrationService(mockHash, mockRepo, mockPass, mockSalt, mockIban);
    }

    @Test
    public void memberServiceNotNull() {
        assertThat(clientRegistrationService).isNotNull();
    }

    UserDTO userDTO = new UserDTO("Dagobert", "", "Duck",
            LocalDate.parse("1990-10-10"), 1, "dd@duck.nl", "username", "HOOFDLETTERs.1",
            "wasstraat", 1, "", "1111xx", "Den Haag",
            "Nederland");

    @Test
    public void registerTest() {
        UserDetails result = clientRegistrationService.register(userDTO);
        System.out.println(result.getUsername());
        System.out.println(result.getPassword());
        System.out.println(result.getSalt());
        System.out.println(result);
    }

}