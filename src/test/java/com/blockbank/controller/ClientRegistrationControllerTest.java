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
import org.springframework.web.bind.annotation.RequestParam;

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
                LocalDate.parse("1990-10-10"), 1, "dd@duck.nl", "username", "123HPss82cmdmslZa!",
                "wasstraat", 1, "", "1111xx", "Den Haag",
                "Nederland");

        Address address = new Address(userDTO.getAddress(), userDTO.getHouseNumber(), userDTO.getAffix(),
                userDTO.getPostalCode(), userDTO.getCity(), userDTO.getCountry());
        ClientDetails clientDetails = new ClientDetails(userDTO.getFirstname(), userDTO.getPrefix(),
                userDTO.getLastname(), userDTO.getDateOfBirth(), userDTO.getBsn(), userDTO.getEmailAddress());
        UserDetails userDetails = new UserDetails(userDTO.getUsername(),userDTO.getPassword(), "12345",
                clientDetails, address);


        Mockito.when(clientRegistrationService.register(userDTO)).thenReturn(userDetails);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/register");
//        request.equals(userDTO);
        request.param("firstname" , "Dagobert");
        request.param("prefix" , "");
        request.param("lastname" , "Duck");
        request.param("dateOfBirth" , "1990-10-10");
        request.param("bsn" , "1");
        request.param("emailAddress" , "dd@duck.nl");
        request.param("username" , "username");
        request.param("password" , "123HPss82cmdmslZa!");
        request.param("address" , "wasstraat");
        request.param("houseNumber" , "1");
        request.param("affix" , "");
        request.param("postalCode" , "1111xx");
        request.param("city" , "Den Haag");
        request.param("country" , "Nederland");

        try {
            ResultActions actions = mockMvc.perform(request);
            MockHttpServletResponse response = actions.andDo(print()).andReturn().getResponse();
            //MockHttpServletResponse response = actions.andExpect(status().isOk()).andDo(print()).andReturn().getResponse();
            System.out.println(response.getContentAsString());
            assertThat(response.getContentType()).isEqualTo("application/json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}