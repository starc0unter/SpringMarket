package com.geekbrains.septembermarket;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void tryToStart() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.core.StringContains.containsString("September Market")));
    }

    @Test
    public void securityAccessDeniedTest() throws Exception {
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(username = "+79380000000", roles = "ADMIN")
    // @WithAnonymousUser
    public void securityAccessAllowedTest() throws Exception {
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void correctLogin() throws Exception {
        mockMvc.perform(formLogin("/authenticateTheUser").user("11111111").password("100"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void badCredentials() throws Exception {
        mockMvc.perform(formLogin("/authenticateTheUser").user("admin").password("100"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}
