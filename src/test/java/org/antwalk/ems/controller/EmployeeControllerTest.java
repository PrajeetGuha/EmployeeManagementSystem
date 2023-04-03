package org.antwalk.ems.controller;

import org.antwalk.ems.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("test1employee")
public class EmployeeControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@Test
	void testEditEmployee() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/employee/dashboard"))
			.andExpect(MockMvcResultMatchers.model().attribute("status", "SUCCESS"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.view().name("myProfile"));
	}
 
	@Test
	void testEmployeePersonalDetails() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/employee/employeepersonaldetails"))
			.andExpect(MockMvcResultMatchers.model().attribute("status", "SUCCESS"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.view().name("personalDetails"));
	}

	@Test
	void testApplyLeaves() throws Exception {

		String validDate = "2023-12-10";
		mvc.perform(MockMvcRequestBuilders.post("/employee/applyLeave")
				.param("leaveType", "CL")
				.param("leaveAppliedFor",validDate)
				.param("leaveReason","Test Reason"))
			.andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
			.andExpect(MockMvcResultMatchers.redirectedUrl("/employee/leaveApplication?pg=1"));

		String invalidDate = "2023-15-32";
		mvc.perform(MockMvcRequestBuilders.post("/employee/applyLeave")
				.param("leaveType", "CL")
				.param("leaveAppliedFor",invalidDate)
				.param("leaveReason","Test Reason"))
			.andExpect(MockMvcResultMatchers.flash().attribute("status", "FAILED"))
			.andExpect(MockMvcResultMatchers.redirectedUrl("/employee/leaveApplication?pg=1"));
	}

	@Test
	void testDeleteFamilyMember() throws Exception {

		String validFid = "1";
		mvc.perform(MockMvcRequestBuilders.post("/employee/deletefamilymember?fid="+validFid))
			.andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
			.andExpect(MockMvcResultMatchers.redirectedUrl("/employee/familyDetails"));

		String notExistFid = "10";
		mvc.perform(MockMvcRequestBuilders.post("/employee/deletefamilymember?fid="+notExistFid))
			.andExpect(MockMvcResultMatchers.flash().attribute("status", "FAILED"))
			.andExpect(MockMvcResultMatchers.redirectedUrl("/employee/familyDetails"));

		String FidOfOtherEmp = "3";
		mvc.perform(MockMvcRequestBuilders.post("/employee/deletefamilymember?fid="+FidOfOtherEmp))
			.andExpect(MockMvcResultMatchers.flash().attribute("status", "FAILED"))
			.andExpect(MockMvcResultMatchers.redirectedUrl("/employee/familyDetails"));
	}

	@Test
	void testDeleteQualification() throws Exception {

		String validQid = "1";
		mvc.perform(MockMvcRequestBuilders.post("/employee/deleteQualification?qid="+validQid))
			.andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
			.andExpect(MockMvcResultMatchers.redirectedUrl("/employee/qualificationdetails"));

		String notExistQid = "10";
		mvc.perform(MockMvcRequestBuilders.post("/employee/deleteQualification?qid="+notExistQid))
			.andExpect(MockMvcResultMatchers.flash().attribute("status", "FAILED"))
			.andExpect(MockMvcResultMatchers.redirectedUrl("/employee/qualificationdetails"));

		String QidOfOtherEmp = "3";
		mvc.perform(MockMvcRequestBuilders.post("/employee/deleteQualification?qid="+QidOfOtherEmp))
			.andExpect(MockMvcResultMatchers.flash().attribute("status", "FAILED"))
			.andExpect(MockMvcResultMatchers.redirectedUrl("/employee/qualificationdetails"));
	}

	@Test
	void testDeleteProfession() throws Exception {

		String validPid = "1";
		mvc.perform(MockMvcRequestBuilders.post("/employee/deleteProfession?pid="+validPid))
			.andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
			.andExpect(MockMvcResultMatchers.redirectedUrl("/employee/professionaldetails"));

		String notExistPid = "10";
		mvc.perform(MockMvcRequestBuilders.post("/employee/deleteProfession?pid="+notExistPid))
			.andExpect(MockMvcResultMatchers.flash().attribute("status", "FAILED"))
			.andExpect(MockMvcResultMatchers.redirectedUrl("/employee/professionaldetails"));

		String PidOfOtherEmp = "3";
		mvc.perform(MockMvcRequestBuilders.post("/employee/deleteProfession?pid="+PidOfOtherEmp))
			.andExpect(MockMvcResultMatchers.flash().attribute("status", "FAILED"))
			.andExpect(MockMvcResultMatchers.redirectedUrl("/employee/professionaldetails"));
	}

	@Test
	void testLeaveApplicationView() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/employee/leaveApplication?pg=1"))
		.andExpect(MockMvcResultMatchers.view().name("applyLeave"))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.model().attribute("status", "SUCCESS"));
	}

	@Test
	void testPersonalDetailsOfEmployee() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/employee/personaldetailsofemployee")
			.param("maritalStatus", "single")
			.param("permaAddress", "Lenin Street Kolkata")
			.param("primaryContactno", "9871002345")
			.param("emergencyContactno", "9871002345")
			.param("emailId", "abc@test.com")
			.param("presentAddress", "Lenin Street Kolkata")
			.param("nationality", "indian")
			.param("bloodGrp", "B+")
			.param("adhhaarno", "123455678901")
			.param("pancardno", "1234567890")
			.param("passportno", "1234567890")
		)
		.andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/employee/employeepersonaldetails"));
	}

	@Test
	void testResign() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/employee/resign"))
		.andExpect(MockMvcResultMatchers.model().attribute("status", "SUCCESS"))
		.andExpect(MockMvcResultMatchers.view().name("applyResignation"));
	}

	@Test
	void testApplyResignation() throws Exception{
		mvc.perform(MockMvcRequestBuilders.post("/employee/applyResignation")
			.param("resignationReason","test Reason"))
		.andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/employee/resign"));
	}

	@Test
	void testQualificationDetails() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/employee/qualificationdetails"))
		.andExpect(MockMvcResultMatchers.model().attribute("status", "SUCCESS"))
		.andExpect(MockMvcResultMatchers.view().name("myQualification"));
	}

	@Test
	void testAddQualification() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/employee/addQualification")
		.param("qual", "Higher Secondary")
		.param("percent","80")
		.param("startDate","2017-07-31")
		.param("endDate","2019-07-31"))
		.andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/employee/qualificationdetails"));
	}

	@Test
	void testFamilyDetails() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/employee/familyDetails"))
		.andExpect(MockMvcResultMatchers.model().attribute("status", "SUCCESS"))
		.andExpect(MockMvcResultMatchers.view().name("myFamily"));
	}

	@Test
	void testAddFamily() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/employee/addFamily")
		.param("name", "Ma")
		.param("dob","1989-05-23")
		.param("relation","Mother")
		.param("occupation","Soldier")
		.param("contactno","1234567890"))
		.andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/employee/familyDetails"));
	}

	@Test
	void testProfessionalDetails() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/employee/professionaldetails"))
		.andExpect(MockMvcResultMatchers.model().attribute("status", "SUCCESS"))
		.andExpect(MockMvcResultMatchers.view().name("myProfession"));
	}

	@Test
	void testAddProfession() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/employee/addProfession")
		.param("nameOfPrevOrg", "Cisco")
		.param("designation","SDE")
		.param("fromDate","1989-12-13")
		.param("toDate","2017-12-13"))
		.andExpect(MockMvcResultMatchers.flash().attribute("status", "SUCCESS"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/employee/professionaldetails"));
	}

}
