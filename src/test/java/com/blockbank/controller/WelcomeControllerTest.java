package com.blockbank.controller;

/**
 * @author Alex Shijan
 */

import com.blockbank.contoller.WelcomeController;
import com.blockbank.service.TokenService;
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

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

@WebMvcTest(WelcomeController.class)
class WelcomeControllerTest {

    private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    private MockMvc mockMvc;

    private WelcomeControllerTest welcomeControllerTest;

    @MockBean
    TokenService tokenService;

    @Autowired
    public WelcomeControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        logger.info("New ClientRegistrationControllerTest");
    }

    @Test
    void loadWelcomePage() {
        String token = "{iss=\"Blockbank\", role=\"client\", username=\"Harold\"}";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/welcome/get/json").header("Authorization", token);
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