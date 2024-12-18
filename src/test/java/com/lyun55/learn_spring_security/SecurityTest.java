package com.lyun55.learn_spring_security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "lyun55", roles = "USER")
    void shouldAllowAccessWhenUsernameMatches() throws Exception {
        mockMvc.perform(get("/users/lyun55/todos"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "lyun55", roles = "USER")
    void shouldDenyAccessWhenUsernameDoesNotMatch() throws Exception {
        mockMvc.perform(get("/users/anotheruser/todos"))
                .andExpect(status().isForbidden());
    }
}