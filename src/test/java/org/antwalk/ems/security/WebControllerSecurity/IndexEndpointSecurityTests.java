package org.antwalk.ems.security.WebControllerSecurity;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class IndexEndpointSecurityTests {
    
    @Autowired
    private MockMvc mvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void applySecurity() {
        this.mvc = webAppContextSetup(wac)
            .apply(springSecurity(springSecurityFilterChain))
            .build();
    }

    @Test
    public void testIndexPageAsAnonymous() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/")
                .accept(MediaType.TEXT_HTML)
                .with(SecurityMockMvcRequestPostProcessors.anonymous()))
            .andExpect(MockMvcResultMatchers.redirectedUrl("/welcome"));
    }

    @Test
    public void testIndexPageAsAdmin() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/")
                .accept(MediaType.TEXT_HTML)
                .with(SecurityMockMvcRequestPostProcessors.user("admin")))
            .andExpect(MockMvcResultMatchers.redirectedUrl("/welcome"));
    }

    @Test
    public void testIndexPageAsEmployee() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/")
                .accept(MediaType.TEXT_HTML)
                .with(SecurityMockMvcRequestPostProcessors.user("demo")))
            .andExpect(MockMvcResultMatchers.redirectedUrl("/welcome"));
    }
}
