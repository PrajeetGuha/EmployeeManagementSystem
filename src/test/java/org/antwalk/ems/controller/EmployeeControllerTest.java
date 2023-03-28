package org.antwalk.ems.controller;

import org.antwalk.ems.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "testuser", password = "Test123#", roles = "EMP")
public class EmployeeControllerTest {
	  @Autowired
	    private MockMvc mvc;
	    
	    @Test
	    void testEmployeeDashboard() throws Exception {
	        String empIdExists = "1";
	        mvc.perform(MockMvcRequestBuilders.get("/employee/dashboard"))        
	        .andExpect(MockMvcResultMatchers.view().name("myProfile"));

//	        String empIdnotExists = "10";
//	        mvc.perform(MockMvcRequestBuilders.post("/admin/activateUser?search=null&pg=1")
//	        .param("empId", empIdnotExists))        
//	        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/dashboard?search=null&pg=null"))
//	        .andExpect(MockMvcResultMatchers.flash().attribute("status","FAILED"));
	    }

}
