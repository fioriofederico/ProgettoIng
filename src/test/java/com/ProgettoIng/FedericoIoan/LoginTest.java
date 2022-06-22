package com.ProgettoIng.FedericoIoan;

import com.ProgettoIng.FedericoIoan.utils.GenericRestControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginTest extends GenericRestControllerTest {

    @Test
    public void successfulAuthenticationWithUser() throws Exception {
        getMockMvc().perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"ioan-admin\", \"password\": \"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("token")));
    }

    @Test
    public void unsuccessfulAuthenticationWithUser() throws Exception {
        getMockMvc().perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"ioan-admin\", \"password\": \"wrongepwd\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("token")));
    }
}
