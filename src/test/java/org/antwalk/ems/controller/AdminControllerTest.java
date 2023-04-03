package org.antwalk.ems.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matchers.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

import org.antwalk.ems.model.Admin;
import org.antwalk.ems.pojo.SuccessDetails;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsEqual;

// insert into user(username,password,role,is_enabled, table_pk) values('test-admin','$2a$12$I1pwfNEvxdVTY281JTkcO.X0kFAEAWpSuvhpO8KIln9pR6rojoqE2','ROLE_ADMIN',1,1);
// insert into admin(admin_id, admin_name, admin_email) values(1,"Test Admin","test@admin.nrifintech.com");

// insert into employee_details(email_id) values("test1employee@test.com");
// insert into employee(emp_name, gender, designation, department, year_of_experience, grade_level, doj, emptype, work_email, empstatus, employee_details_emp_det_id) values('Test1', 'male', 'ASE', 'administration', 3, 4, '2020-09-23', 'full time', 'test1employee@nrifintech.com','active', 1);
// insert into user(username,password,table_pk) values('test1employee','$2a$12$I1pwfNEvxdVTY281JTkcO.X0kFAEAWpSuvhpO8KIln9pR6rojoqE2',1);

// insert into employee_details(email_id) values("test2employee@test.com");
// insert into employee(emp_name, gender, designation, department, year_of_experience, grade_level, doj, emptype, work_email, empstatus, employee_details_emp_det_id) values('Test2', 'female', 'security', 'DSE', 5, 2, '2023-03-28', 'part time', 'test2employee@nrifintech.com', 'active', 2);
// insert into user(username,password,table_pk) values('test2employee','$2a$12$I1pwfNEvxdVTY281JTkcO.X0kFAEAWpSuvhpO8KIln9pR6rojoqE2',2);

@SpringBootTest
@AutoConfigureMockMvc
// @WithMockUser(username = "test-admin", password = "test", roles = "ADMIN")
@WithUserDetails("test-admin")
public class AdminControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @Test
    void testActivateEmployee() throws Exception {
        String empIdExists = "1";
        mvc.perform(MockMvcRequestBuilders.post("/admin/activateUser?search=null&pg=1")
        .param("empId", empIdExists))
        .andDo(MockMvcResultHandlers.print())         
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/dashboard?search=null&pg=1"))
        .andExpect(MockMvcResultMatchers.flash().attribute("status","SUCCESS"));

        String empIdnotExists = "10";
        mvc.perform(MockMvcRequestBuilders.post("/admin/activateUser?search=null&pg=1")
        .param("empId", empIdnotExists))
        .andDo(MockMvcResultHandlers.print())         
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/dashboard?search=null&pg=1"))
        .andExpect(MockMvcResultMatchers.flash().attribute("status","FAILED"));
    }

    @Test
    void testAddProjectManager() throws Exception {

        String pmExists = "1";
        String projIdExists = "1";
        mvc.perform(MockMvcRequestBuilders.post("/admin/addProjectManager?projId="+projIdExists+"&pg=1")
            .param("pm", pmExists))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
            .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/editableProjectPage?projid="+projIdExists+"&pg=1"));

        String pmNotExists = "3";
        String projIdNotExists = "5";
        mvc.perform(MockMvcRequestBuilders.post("/admin/addProjectManager?projId="+projIdNotExists+"&pg=1")
            .param("pm", pmNotExists))
            .andExpect(MockMvcResultMatchers.flash().attribute("status", "FAILED"))
            .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/editableProjectPage?projid="+projIdNotExists+"&pg=1"));
    }

    @Test
    void testAddTeam() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/admin/addteam")
            .param("teamName","Test Name")
            .param("department", "Administration")
        ).andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/teamallocation?pg=1"));
    }

    @Test
    void testAddTeamManager() throws Exception {
        String teamExists = "5";
        String teamNotExists = "1";
        String pmExists = "1";
        String pmNotExists = "10";

        mvc.perform(MockMvcRequestBuilders.post("/admin/addTeamManager?pg=1&tid="+teamExists)
            .param("teamManagerValues",pmExists))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/editableTeamPage?tid=" + teamExists + "&pg=1"));

        mvc.perform(MockMvcRequestBuilders.post("/admin/addTeamManager?pg=1&tid="+teamExists)
            .param("teamManagerValues",pmNotExists))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "FAILED"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/editableTeamPage?tid=" + teamExists + "&pg=1"));

        mvc.perform(MockMvcRequestBuilders.post("/admin/addTeamManager?pg=1&tid="+teamNotExists)
            .param("teamManagerValues",pmNotExists))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "FAILED"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/editableTeamPage?tid=" + teamNotExists + "&pg=1"));
    }

    @Test
    void testAddTeamMember() throws Exception {
        String tidExists = "5";
        String teamMembersToAdd = "1;";
        mvc.perform(MockMvcRequestBuilders.post("/admin/addTeamMember?pg=1&teamId="+tidExists)
            .param("hiddenFieldOfTeams",teamMembersToAdd))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/editableTeamPage?tid="+tidExists+"&pg=1"));

        teamMembersToAdd = "1;3;5";
        mvc.perform(MockMvcRequestBuilders.post("/admin/addTeamMember?pg=1&teamId="+tidExists)
            .param("hiddenFieldOfTeams",teamMembersToAdd))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "FAILED"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/editableTeamPage?tid="+tidExists+"&pg=1"));

        String tidNotExists = "10";
        mvc.perform(MockMvcRequestBuilders.post("/admin/addTeamMember?pg=1&teamId="+tidNotExists)
            .param("hiddenFieldOfTeams",teamMembersToAdd))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "FAILED"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/editableTeamPage?tid="+tidNotExists+"&pg=1"));
    }

    @Test
    void testAddTeamToProject() throws Exception {
        String projIdExists = "2";
        String teamsToAdd = "5;";
        mvc.perform(MockMvcRequestBuilders.post("/admin/addTeamToProject?pg=1&projId="+projIdExists)
            .param("hiddenFieldOfTeams",teamsToAdd))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/editableProjectPage?projid="+projIdExists+"&pg=1"));

        teamsToAdd = "1;3;5";
        mvc.perform(MockMvcRequestBuilders.post("/admin/addTeamToProject?pg=1&projId="+projIdExists)
            .param("hiddenFieldOfTeams",teamsToAdd))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "FAILED"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/editableProjectPage?projid="+projIdExists+"&pg=1"));

        String projIdNotExists = "10";
        mvc.perform(MockMvcRequestBuilders.post("/admin/addTeamToProject?pg=1&projId="+projIdNotExists)
            .param("hiddenFieldOfTeams",teamsToAdd))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "FAILED"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/editableProjectPage?projid="+projIdNotExists+"&pg=1"));
    }

    @Test
    void testAddUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/admin/addUser")
            .param("name","Test Name")
            .param("email","Test@email.com")
            .param("gender","Male")
            .param("designation","ASE")
            .param("department","security")
            .param("gradeLevel","1")
            .param("doj","2001-04-27")
            .param("emptype", "trainee")
            .param("username","testusername")
            .param("password","Test@1234"))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("redirect:/admin/dashboard?search=null&pg=1"));
    }

    @Test
    void testAddproject() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/admin/addProj")
            .param("projectName","Test Name")
            .param("startDate", "2023-01-01")
            .param("endDate","2023-07-31")
        ).andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/projectallocation?pg=1"));
    }

    @Test
    void testDeleteProject() throws Exception{
        String projIdExists = "1";
        mvc.perform(MockMvcRequestBuilders.post("/admin/deleteProject?projId="+projIdExists+"&pg=1"))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/projectallocation?pg=1"));

        String projIdNotExists = "3";
        mvc.perform(MockMvcRequestBuilders.post("/admin/deleteProject?projId="+projIdNotExists+"&pg=1"))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "FAILED"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/projectallocation?pg=1"));
    }

    @Test
    void testDeleteTeam() throws Exception{
        String teamIdExists = "1";
        mvc.perform(MockMvcRequestBuilders.post("/admin/deleteTeam?teamId="+teamIdExists+"&pg=1"))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/teamallocation?pg=1"));

        String teamIdNotExists = "3";
        mvc.perform(MockMvcRequestBuilders.post("/admin/deleteTeam?teamId="+teamIdNotExists+"&pg=1"))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "FAILED"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/teamallocation?pg=1"));
    }

    @Test
    void testAnalytics() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/admin/analytics"))
        .andExpect(MockMvcResultMatchers.model().attribute("status","SUCCESS"))
        .andExpect(MockMvcResultMatchers.view().name("analytics"));
    }

    @Test
    void testChangePassword() throws Exception {
        String empIdExists = "1";
        mvc.perform(MockMvcRequestBuilders.post("/admin/changePassword?search=null&pg=1")
        .param("empId", empIdExists)
        .param("changedPassword", "Test1@ntech"))
        .andDo(MockMvcResultHandlers.print())        
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/dashboard?search=null&pg=1"))
        .andExpect(MockMvcResultMatchers.flash().attribute("status","SUCCESS"));

        String empIdnotExists = "10";
        mvc.perform(MockMvcRequestBuilders.post("/admin/changePassword?search=null&pg=1")
        .param("empId", empIdnotExists)
        .param("changedPassword","Test10@ntech"))
        .andDo(MockMvcResultHandlers.print())         
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/dashboard?search=null&pg=1"))
        .andExpect(MockMvcResultMatchers.flash().attribute("status","FAILED"));
    }

    @Test
    void testDeactivateEmployee() throws Exception {

        String empIdExists = "1";
        mvc.perform(MockMvcRequestBuilders.post("/admin/deactivateUser?search=null&pg=1")
        .param("empId", empIdExists))
        .andDo(MockMvcResultHandlers.print())        
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/dashboard?search=null&pg=1"))
        .andExpect(MockMvcResultMatchers.flash().attribute("status","SUCCESS"));

        String empIdnotExists = "10";
        mvc.perform(MockMvcRequestBuilders.post("/admin/deactivateUser?search=null&pg=1")
        .param("empId", empIdnotExists))
        .andDo(MockMvcResultHandlers.print())        
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/dashboard?search=null&pg=1"))
        .andExpect(MockMvcResultMatchers.flash().attribute("status","FAILED"));
    }

    @Test
    void testEditableProjectPage() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/admin/editableProjectPage?pg=1&projid=2"))
        .andExpect(MockMvcResultMatchers.model().attribute("status", "SUCCESS"))
        .andExpect(MockMvcResultMatchers.view().name("editableproject"));
    }

    @Test
    void testEditableTeamPage() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/admin/editableTeamPage?pg=1&tid=9"))
        .andExpect(MockMvcResultMatchers.model().attribute("status", "SUCCESS"))
        .andExpect(MockMvcResultMatchers.view().name("editableTeam"));
    }

    @Test
    void testEditemployee() throws Exception {
        String empIdExists = "1";
        mvc.perform(MockMvcRequestBuilders.post("/admin/editemployee?search=null&pg=1&empId="+empIdExists)
            .param("name","Test Name")
            .param("email","Test@email.com")
            .param("gender","Male")
            .param("designation","ASE")
            .param("department","security")
            .param("gradeLevel","1")
            .param("doj","2001-04-27")
            .param("emptype", "trainee")
            .param("username","testusername")
            .param("password","Test@1234"))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/dashboard?search=null&pg=1"));

        String empIdNotExists = "10";
        mvc.perform(MockMvcRequestBuilders.post("/admin/editemployee?search=null&pg=1&empId="+empIdNotExists)
            .param("name","Test Name")
            .param("email","Test@email.com")
            .param("gender","Male")
            .param("designation","ASE")
            .param("department","security")
            .param("gradeLevel","1")
            .param("doj","2001-04-27")
            .param("emptype", "trainee")
            .param("username","testusername")
            .param("password","Test@1234"))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "FAILED"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/dashboard?search=null&pg=1"));
    }

    @Test
    void testEditemployeedetails() throws Exception {

        String empIdExist = "1";
        mvc.perform(MockMvcRequestBuilders
        .get("/admin/editemployeedetails?search=null&pg=1&empId="+empIdExist))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.view().name("editUser"))
        .andExpect(MockMvcResultMatchers.model().attribute("status", "SUCCESS")
        );

        String empIdNotExist = "10";
        mvc.perform(MockMvcRequestBuilders
        .get("/admin/editemployeedetails?search=null&pg=1&empId="+empIdNotExist))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.view().name("editUser"))
        .andExpect(MockMvcResultMatchers.model().attribute("status", "FAILED")
        );
    }

    @Test
    void testGenerateEmployeeReport() throws Exception {

        String empIdExists = "1";
        mvc.perform(MockMvcRequestBuilders.get("/admin/report")
        .param("empId", empIdExists))
        .andExpect(MockMvcResultMatchers.content().contentType("application/octet-stream"));

        String empIdNotExists = "10";
        mvc.perform(MockMvcRequestBuilders.get("/admin/report")
        .param("empId", empIdNotExists))
        .andExpect(MockMvcResultMatchers.content().string(Matchers.blankOrNullString()));
    }

    @Test
    void testLeaveAction() throws Exception {
        String lidExists = "1";
        mvc.perform(MockMvcRequestBuilders.post("/admin/leaveAction?lid="+lidExists+"&pg=1&approve=true"))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/leaveApproval?pg=1"));

        lidExists = "2";
        mvc.perform(MockMvcRequestBuilders.post("/admin/leaveAction?lid="+lidExists+"&pg=1&approve=false"))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/leaveApproval?pg=1"));

        String lidNotExists = "10";
        mvc.perform(MockMvcRequestBuilders.post("/admin/leaveAction?lid="+lidNotExists+"&pg=1&approve=true"))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "FAILED"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/leaveApproval?pg=1"));
    }

    @Test
    void testLeaveApprovaldashboard() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/admin/leaveApproval?pg=1"))
        .andExpect(MockMvcResultMatchers.model().attribute("status", "SUCCESS"))
        .andExpect(MockMvcResultMatchers.view().name("leaveApproval"));
    }

    @Test
    void testProjectallocation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/admin/projectallocation?pg=1"))
        .andExpect(MockMvcResultMatchers.view().name("projectallocation"))
        .andExpect(MockMvcResultMatchers.model().attribute("status","SUCCESS"));
    }

    @Test
    void testResignAction() throws Exception {
        String resignationExists = "1";
        mvc.perform(MockMvcRequestBuilders.post("/admin/resignAction?rid="+resignationExists+"&pg=1&approve=true"))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/resignationApproval?pg=1"));

        resignationExists = "2";
        mvc.perform(MockMvcRequestBuilders.post("/admin/resignAction?rid="+resignationExists+"&pg=1&approve=false"))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/resignationApproval?pg=1"));

        String resignationNotExists = "10";
        mvc.perform(MockMvcRequestBuilders.post("/admin/resignAction?rid="+resignationNotExists+"&pg=1&approve=false"))
        .andExpect(MockMvcResultMatchers.flash().attribute("status", "FAILED"))
        .andExpect(MockMvcResultMatchers.redirectedUrl("/admin/resignationApproval?pg=1"));
    }

    @Test
    void testResignationApprovaldashboard() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/admin/resignationApproval?pg=1"))
        .andExpect(MockMvcResultMatchers.model().attribute("status", "SUCCESS"))
        .andExpect(MockMvcResultMatchers.view().name("resignationApproval"));
    }

    @Test
    void testTeamallocation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/admin/teamallocation?pg=1"))
        .andExpect(MockMvcResultMatchers.view().name("teamallocation"))
        .andExpect(MockMvcResultMatchers.model().attribute("status","SUCCESS"));
    }
}
