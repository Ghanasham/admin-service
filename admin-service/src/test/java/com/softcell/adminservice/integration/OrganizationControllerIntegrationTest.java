package com.softcell.adminservice.integration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.softcell.adminservice.domain.ContactDetails;
import com.softcell.adminservice.domain.ContactDetails.State;
import com.softcell.adminservice.domain.Organization;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrganizationControllerIntegrationTest {

	@Autowired
    private TestRestTemplate restTemplate;

	@Test
	public void testUpdateOrganization() throws Exception {
		
		Organization org = new Organization();
		org.setOrgId(1L);
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
    	
    	restTemplate.put("http://localhost:8080/admin/org/1", org);
    	
    	Organization getOrg = restTemplate.getForObject("http://localhost:8080/admin/org/1", Organization.class);
    	
    	Assert.assertEquals(org, getOrg);
	}
	
}
