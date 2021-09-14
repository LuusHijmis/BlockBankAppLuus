package com.blockbank.controller;
/*@author Karish Resodikromo
 * Deze klasse test de ClientRegistrationController
 * */

import com.blockbank.contoller.ClientRegistrationController;
import com.blockbank.database.domain.Address;
import com.blockbank.database.domain.ClientDetails;
import com.blockbank.database.domain.RegistrationDTO;
import com.blockbank.database.domain.UserDetails;
import com.blockbank.service.ClientRegistrationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientRegistrationController.class)
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
        RegistrationDTO registrationDTO = new RegistrationDTO("Donald", "", "Duck",
                LocalDate.parse("1990-10-10"), 1, "d10@duck.nl", "gebruiker", "HOOFDLETTERs.1",
                "wasstraat", 1, "", "1111xx", "Den Haag",
                "Nederland");

        Address address = new Address(registrationDTO.getAddress(), registrationDTO.getHouseNumber(), registrationDTO.getAffix(),
                registrationDTO.getPostalCode(), registrationDTO.getCity(), registrationDTO.getCountry());
        ClientDetails clientDetails = new ClientDetails(registrationDTO.getFirstname(), registrationDTO.getPrefix(),
                registrationDTO.getLastname(), registrationDTO.getDateOfBirth(), registrationDTO.getBsn(), registrationDTO.getEmailAddress());
        UserDetails userDetails = new UserDetails(registrationDTO.getUsername(), registrationDTO.getPassword(), "12345",
                clientDetails, address);


        Mockito.when(clientRegistrationService.register(registrationDTO)).thenReturn(userDetails);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registerDTO");
        request.contentType(MediaType.APPLICATION_JSON);
        request.content("{\"username\":\"gebruiker\", " +
                "\"password\":\"HOOFDLETTERs.1\", " +
                "\"firstname\":\"Donald\", " +
                "\"prefix\":\"\", " +
                "\"lastname\":\"Duck\", " +
                "\"dateOfBirth\":\"1990-10-10\", " +
                "\"bsn\":\"1\", " +
                "\"emailAddress\":\"d10@duck.nl\", " +
                "\"address\":\"wasstraat\", " +
                "\"houseNumber\":\"1\", " +
                "\"affix\":\"\", " +
                "\"postalCode\":\"1111xx\", " +
                "\"city\":\"Den Haag\", " +
                "\"country\":\"Nederland\" }");
        try {
            UserDetails details = clientRegistrationService.register(registrationDTO);
            assertThat(details).isNotNull();
            assertThat(details).isEqualTo(userDetails);
            ResultActions actions = mockMvc.perform(request);
            MockHttpServletResponse response = actions.andExpect(status().isOk()).andDo(print()).andReturn().getResponse();
            System.out.println(response.getContentAsString());
            assertThat(response.getContentType()).isEqualTo("application/json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}