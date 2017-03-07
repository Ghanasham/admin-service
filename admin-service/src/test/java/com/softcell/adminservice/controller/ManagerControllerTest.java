package com.softcell.adminservice.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.softcell.adminservice.domain.ApplicationType;
import com.softcell.adminservice.domain.Manager;
import com.softcell.adminservice.domain.ManagerPrimaryKey;
import com.softcell.adminservice.domain.Organization;
import com.softcell.adminservice.service.db.DBManagerService;

@RunWith(SpringRunner.class)
@WebMvcTest(ManagerController.class)
public class ManagerControllerTest {

	@Autowired
    private MockMvc mvc;

    @MockBean
    private DBManagerService managerService;

    @Test
    public void testGetManager() throws Exception {
    	
    	Organization organization = new Organization();
    	organization.setOrgId(1L);
    	organization.setName("HDFC");
    	
    	Manager manager = new Manager();
    	manager.setEmployeeId(12345L);
    	manager.setAppType(ApplicationType.HOME_LOAN);
    	manager.setLevel((byte)1);
    	manager.setOrgId(organization);
    	manager.setRole("Risk Manager");
    	
    	given(this.managerService.getManager(new ManagerPrimaryKey(12345L, ApplicationType.HOME_LOAN)))
        .willReturn(manager);

		this.mvc.perform(get("/admin/org/1/manager/12345/HOME_LOAN"))
		.andExpect(jsonPath("$.employeeId").value("12345"))
		.andExpect(jsonPath("$.appType").value("HOME_LOAN"))
		.andExpect(jsonPath("$.level").value(1))
		.andExpect(jsonPath("$.role").value("Risk Manager"));
    }

	
}
