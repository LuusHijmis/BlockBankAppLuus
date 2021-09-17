package com.blockbank.controller;

import com.blockbank.contoller.LoginController;
import com.blockbank.database.repository.RootRepository;
import com.blockbank.service.LoginService;
import com.blockbank.service.LoginValidationService;
import com.blockbank.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author hannahvd
 */

/*
https://rieckpil.de/guide-to-testing-spring-boot-applications-with-mockmvc/
https://spring.io/guides/gs/testing-web/
https://reflectoring.io/spring-boot-web-controller-test/ <<<
 */

@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Bean
    public LoginService loginService() {
        return Mockito.mock(LoginService.class);
    }

    @Autowired
    private WebApplicationContext webAppContext;

//    @MockBean
//    private TokenService tokenService;

//    @BeforeEach
//    void setup() throws Exception {
//        this.mockMvc = MockMvcBuilders
//                .webAppContextSetup(this.webAppContext)
//                .build();
//    }

    @BeforeEach
    void setup() throws Exception {
        //this.mockMvc = MockMvcBuilders.webAppContextSetup()
    }

    @Autowired
    LoginControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

//    @Test
//    void verifyHTTPrequest() throws Exception {
//        mockMvc.perform(post("/login")
//                .contentType("application/json")) //? //Set the 'Content-Type' header of the request as a raw String value
//                .andExpect(status().isOk());
//    }

//    @Test
//    void mockLoginService() {
//        when(loginService.login("hannahvd", "hannahVD123."))
//                .thenReturn(tokenService.issueToken("hannahvd")); //return niet ok?
//    }

    @Test
    void login() throws Exception {
        //when(loginService.login("hannahvd", "hannahVD123.")).thenReturn("jwtUser");

        mockMvc.perform(post("/login"))
                .andDo(print())
                .andExpect(status().isOk());
                //.andExpect(content().string(containsString("Incorrect credentials")));
    }

}