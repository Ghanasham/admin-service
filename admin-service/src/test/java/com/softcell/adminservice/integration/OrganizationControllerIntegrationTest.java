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
    	
    	Organization postOrg = restTemplate.postForObject("http://localhost:8080/admin/org", org, Organization.class);
    	
    	Organization getOrg = restTemplate.getForObject("http://localhost:8080/admin/org/" + postOrg.getOrgId() , Organization.class);
    	
    	Assert.assertEquals(postOrg, getOrg);
	}
	
	
    @Test
    public void testUpdateOrganization() throws Exception {
        
    	Organization org = restTemplate.getForObject("http://localhost:8080/admin/org/1", Organization.class);
    	org.setName(org.getName() + " Bank");
    	
    	restTemplate.put("http://localhost:8080/admin/org/1", org);
    	
    	Organization org1 = restTemplate.getForObject("http://localhost:8080/admin/org/1", Organization.class);
    	
    	Assert.assertEquals(org.getName(), org1.getName());
    	    	
    }
    
}
