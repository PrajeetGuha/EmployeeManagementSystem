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
public class WelcomeEndpointSecurityTests {
    
    @Autowired
    private MockMvc mvc;

    @Test
    public void testWelcomePageAsAnonymous() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/welcome")
                .accept(MediaType.TEXT_HTML)
                .with(SecurityMockMvcRequestPostProcessors.anonymous()))
            .andExpect(MockMvcResultMatchers.view().name("welcome"));
    }

    @Test
    public void testWelcomePageAsAdmin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/welcome")
                .accept(MediaType.TEXT_HTML)
                .with(SecurityMockMvcRequestPostProcessors.user("admin")))
            .andExpect(MockMvcResultMatchers.view().name("welcome"));
    }

    @Test
    public void testWelcomePageAsEmployee() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/welcome")
                .accept(MediaType.TEXT_HTML)
                .with(SecurityMockMvcRequestPostProcessors.user("demo")))
            .andExpect(MockMvcResultMatchers.view().name("welcome"));
    }
}
