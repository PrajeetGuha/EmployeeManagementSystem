package org.antwalk.ems.security.WebControllerSecurity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class LoginEndpointSecurityTests {
    
    @Autowired
    private MockMvc mvc;

    @Test
    public void testLoginPageAsAnonymous() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/login")
                .accept(MediaType.TEXT_HTML)
                .with(SecurityMockMvcRequestPostProcessors.anonymous()))
            .andExpect(MockMvcResultMatchers.view().name("login"));
    }

    @Test
    public void testIndexPageAsAdmin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/login")
                .accept(MediaType.TEXT_HTML)
                .with(SecurityMockMvcRequestPostProcessors.user("admin")))
            .andExpect(MockMvcResultMatchers.redirectedUrl("/loggedin"));
    }

    @Test
    public void testIndexPageAsEmployee() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/login")
                .accept(MediaType.TEXT_HTML)
                .with(SecurityMockMvcRequestPostProcessors.user("demo")))
            .andExpect(MockMvcResultMatchers.redirectedUrl("/loggedin"));
    }
}
