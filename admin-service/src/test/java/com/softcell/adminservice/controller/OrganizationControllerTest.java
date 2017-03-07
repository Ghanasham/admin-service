package com.softcell.adminservice.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.UUID;

import com.softcell.adminservice.controller.OrganizationController;
import com.softcell.adminservice.domain.Organization;
import com.softcell.adminservice.service.db.DBOrganizationService;

@RunWith(SpringRunner.class)
@WebMvcTest(OrganizationController.class)
public class OrganizationControllerTest {

	
	@Autowired
    private MockMvc mvc;

    @MockBean
    private DBOrganizationService organizationService;

    @Test
    public void testGetOrganization() throws Exception {
        
    	Organization organization = new Organization();
    	organization.setOrgId(12345L);
    	organization.setName("SBI");
    	
    	given(this.organizationService.getOrganization(12345L))
                .willReturn(organization);
        
        this.mvc.perform(get("/admin/org/12345"))
        .andExpect(jsonPath("$.id").value("12345"))
        .andExpect(jsonPath("$.name").value("SBI"));
                
    }
    
}
