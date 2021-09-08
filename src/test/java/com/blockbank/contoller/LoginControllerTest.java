package com.blockbank.contoller;

import com.blockbank.database.repository.RootRepository;
import com.blockbank.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author hannahvd
 */

@WebMvcTest(LoginController.class)
class LoginControllerTest {

    private RootRepository rootRepository;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService service;

    //TODO: rootrepository? yup.

    @Test
    void login() throws Exception {
        when(service.login("hannahvd", "hannahVD123.")).thenReturn("*tokenvalue*");
        this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, Mock")));
    }

}