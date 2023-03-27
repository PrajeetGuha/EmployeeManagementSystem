package org.antwalk.ems.controller;

import org.antwalk.ems.security.AuthenticationSystem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
public class WebControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @Test
    @WithAnonymousUser
    void testDashboardAsAnonymous() throws Exception {
        mvc.perform(
            MockMvcRequestBuilders.get("/loggedin")
            .contentType(MediaType.ALL)
        )
        .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDashboardAsAdmin() throws Exception {
        mvc.perform(
            MockMvcRequestBuilders.get("/loggedin")
            .contentType(MediaType.ALL)
        )
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/dashboard?search=null&pg=1"));
    }

    @Test
    @WithMockUser(roles = "EMP")
    void testDashboardAsEmployee() throws Exception {
        mvc.perform(
            MockMvcRequestBuilders.get("/loggedin")
            .contentType(MediaType.ALL)
        )
        .andExpect(MockMvcResultMatchers.redirectedUrl("/employee/dashboard"));
    }

    @Test
    void testIndex() throws Exception {
        mvc.perform(
            MockMvcRequestBuilders.get("/")
            .contentType(MediaType.ALL)
        )
        .andExpect(MockMvcResultMatchers.redirectedUrl("/welcome"));
    }

    @Test
    @WithAnonymousUser
    void testLoginAsAnonymous() throws Exception {
        mvc.perform(
            MockMvcRequestBuilders.get("/login")
            .contentType(MediaType.ALL)
        )
        .andExpect(MockMvcResultMatchers.view().name("login"));
    }

    @Test
    @WithMockUser(roles = "EMP")
    void testLoginAsEmployee() throws Exception {
        mvc.perform(
            MockMvcRequestBuilders.get("/login")
            .contentType(MediaType.ALL)
        )
        .andExpect(MockMvcResultMatchers.redirectedUrl("/loggedin"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testLoginAsAdmin() throws Exception {
        mvc.perform(
            MockMvcRequestBuilders.get("/login")
            .contentType(MediaType.ALL)
        )
        .andExpect(MockMvcResultMatchers.redirectedUrl("/loggedin"));
    }

    @Test
    void testWelcome() throws Exception {
        mvc.perform(
            MockMvcRequestBuilders.get("/welcome")
            .contentType(MediaType.ALL)
        )
        .andExpect(MockMvcResultMatchers.view().name("welcome"));
    }
}
