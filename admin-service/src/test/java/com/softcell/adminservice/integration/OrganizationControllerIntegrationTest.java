package com.softcell.adminservice.integration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.softcell.adminservice.controller.OrganizationController;
import com.softcell.adminservice.domain.ApplicationType;
import com.softcell.adminservice.domain.ContactDetails;
import com.softcell.adminservice.domain.ContactDetails.State;
import com.softcell.adminservice.domain.Manager;
import com.softcell.adminservice.domain.Organization;
import com.softcell.adminservice.service.OrganizationService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrganizationControllerIntegrationTest {

	@Autowired
    private TestRestTemplate restTemplate;

	/*@Test
	public void testCreateOrganization() throws Exception {
		
		Organization org = new Organization();
		org.setName("HDFC");
    	ContactDetails contact = new ContactDetails();
    	contact.setAddressLine1("Addr1");
    	contact.setAddressLine2("Addr2");
    	contact.setCity("Pune");
    	contact.setEmail("test@gmail.com");
    	contact.setPhone("1234567899");
    	contact.setPinCode(411027);
    	contact.setState(State.Maharashtra);
    	
    	org.setOrgContactDetails(contact);
    	
    	restTemplate.postForLocation("http://localhost:8080/admin/org", org);
    	
    	
	}*/
	
    @Test
    public void testUpdateOrganization() throws Exception {
        
    	Organization org = restTemplate.getForObject("http://localhost:8080/admin/org/1", Organization.class);
    	
    	Manager m1 = new Manager();
    	m1.setOrgId(3L);
    	m1.setEmployeeId(1L);
    	m1.setAppType(ApplicationType.HOME_LOAN);
    	m1.setLevel((byte) 0);
    	m1.setRole("Risk Manager");
    	
    	
    	Manager m2 = new Manager();
    	m2.setOrgId(3L);
    	m2.setEmployeeId(2L);
    	m2.setAppType(ApplicationType.HOME_LOAN);
    	m2.setLevel((byte) 0);
    	m2.setRole("Risk Manager");
    	
    	
    	Manager m3 = new Manager();
    	m3.setOrgId(3L);
    	m3.setEmployeeId(3L);
    	m3.setAppType(ApplicationType.HOME_LOAN);
    	m3.setLevel((byte) 1);
    	m3.setRole("Finance Manager");
    	
    	
    	Manager m4 = new Manager();
    	m4.setOrgId(3L);
    	m4.setEmployeeId(4L);
    	m4.setAppType(ApplicationType.HOME_LOAN);
    	m4.setLevel((byte) 1);
    	m4.setRole("Finance Manager");
    	
    	
    	Set<Manager> managers = new HashSet<>();
    	managers.add(m1);
    	managers.add(m2);
    	managers.add(m3);
    	managers.add(m4);
    	
    	org.setManagers(managers);
    	
    	restTemplate.put("http://localhost:8080/admin/org/1", org);
    	
    	Organization org1 = restTemplate.getForObject("http://localhost:8080/admin/org/1", Organization.class);
    	
    	
    	Assert.assertEquals(4, org1.getManagers().size());
    	
    	
    }
    
}
