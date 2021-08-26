package com.blockbank.service;

import com.blockbank.database.domain.UserDTO;
import com.blockbank.database.domain.UserDetails;
import com.blockbank.database.repository.RootRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ClientRegistrationServiceTest {

    ClientRegistrationService clientRegistrationService;

    UserDTO userDTO = new UserDTO("Dagobert", "", "Duck",
            LocalDate.parse("1990-10-10"), 1, "dd@duck.nl", "username", "HOOFDLETTERs.1",
            "wasstraat", 1, "", "1111xx", "Den Haag",
            "Nederland");

    @Test
    public void registerTest() {
        UserDetails result = clientRegistrationService.register(userDTO);
        assertThat(result).isNotNull();
    }

}