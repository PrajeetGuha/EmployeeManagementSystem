package org.antwalk.ems.security.WebControllerSecurity;

import org.antwalk.ems.security.SecurityMokito;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class LoggedInEndpointSecurityTests {
    
    @Autowired
    private MockMvc mvc;

    @Test
    public void testLoggedInAsAnonymous() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/loggedin")
                .accept(MediaType.TEXT_HTML))
            .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
    }

    // @Test
    // public void testLoggedInAsAdmin() throws Exception {
    //     mvc.perform(MockMvcRequestBuilders.get("/loggedin")
    //             .accept(MediaType.TEXT_HTML)
    //             .with(SecurityMockMvcRequestPostProcessors.user("admin")))
    //         .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/dashboard"));
    // }

    // @Test
    // public void testLoggedInAsEmployee() throws Exception {
    //     mvc.perform(MockMvcRequestBuilders.get("/loggedin")
    //             .accept(MediaType.TEXT_HTML)
    //             .with(SecurityMockMvcRequestPostProcessors.user("demo")))
    //         .andExpect(MockMvcResultMatchers.redirectedUrl("/employee/dashboard"));
    // }
}
