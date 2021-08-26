package com.blockbank.controller;
/*@author Karish Resodikromo
 * Deze klasse test de ClientRegistrationController
 * */

import com.blockbank.database.domain.Address;
import com.blockbank.database.domain.ClientDetails;
import com.blockbank.database.domain.UserDTO;
import com.blockbank.database.domain.UserDetails;
import com.blockbank.service.ClientRegistrationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientRegistrationControllerTest.class)
public class ClientRegistrationControllerTest {


    private final Logger logger = LoggerFactory.getLogger(ClientRegistrationControllerTest.class);

    @MockBean
    private ClientRegistrationService clientRegistrationService;

    private MockMvc mockMvc;

    @Autowired
    public ClientRegistrationControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        logger.info("New ClientRegistrationControllerTest");
    }

    //TODO TEST
    @Test
    public void registerTest() {
/*        ClientDetails testClientDetails = new ClientDetails("Dagobert", "", "Duck",
                dateOfBirth, 1, "dd@duck.nl");
        Address testAdress = new Address("wasstraat", 1, "", "1111xx", "Den Haag",
                "Nederland");
        UserDetails testUser = new UserDetails("username", "hallometmij", "salt", testClientDetails,
                testAdress);*/
        UserDTO userDTO = new UserDTO("Dagobert", "", "Duck",
                LocalDate.parse("1990-10-10"), 1, "dd@duck.nl", "username", "hallometmij",
                "wasstraat", 1, "", "1111xx", "Den Haag",
                "Nederland");
        Mockito.when(clientRegistrationService.register(userDTO)).thenReturn(true);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/register");
        request.equals(userDTO);
        try {
            ResultActions actions = mockMvc.perform(request);
            MockHttpServletResponse response = actions.andExpect(status().isOk()).andDo(print()).andReturn().getResponse();
            System.out.println(response.getContentAsString());
            assertThat(response.getContentType()).isEqualTo("application/json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}