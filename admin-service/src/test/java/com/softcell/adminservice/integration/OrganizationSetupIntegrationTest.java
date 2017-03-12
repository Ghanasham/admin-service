package com.softcell.adminservice.integration;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.softcell.adminservice.domain.ApplicationType;
import com.softcell.adminservice.domain.ContactDetails;
import com.softcell.adminservice.domain.Manager;
import com.softcell.adminservice.domain.Organization;
import com.softcell.adminservice.domain.ContactDetails.State;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrganizationSetupIntegrationTest {

	@Autowired
    private TestRestTemplate restTemplate;
	
	@Test
	public void testCreateSetup(){
		Organization org = createOrganization();
		createManagerHierarchy(org);
		
	}
	
	private Organization createOrganization(){
		
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
    	
    	return org;
	}
	
	private void createManagerHierarchy(Organization organization){
		
		List<Manager> managers = Arrays.asList(new Manager(12341L, ApplicationType.HOME_LOAN, (byte)1, organization, "Underwriter"),
    			new Manager(12342L, ApplicationType.HOME_LOAN, (byte)1, organization, "Underwriter"),
    			new Manager(12343L, ApplicationType.HOME_LOAN, (byte)1, organization, "Underwriter"),
    			new Manager(12344L, ApplicationType.HOME_LOAN, (byte)1, organization, "Underwriter"),
    			new Manager(12345L, ApplicationType.HOME_LOAN, (byte)2, organization, "Risk Manager"),
    			new Manager(12346L, ApplicationType.HOME_LOAN, (byte)2, organization, "Risk Manager"),
    			new Manager(12347L, ApplicationType.HOME_LOAN, (byte)2, organization, "Risk Manager"),
    			new Manager(12348L, ApplicationType.HOME_LOAN, (byte)3, organization, "Branch Manager"),
    			new Manager(12349L, ApplicationType.HOME_LOAN, (byte)3, organization, "Branch Manager"),
    			new Manager(12350L, ApplicationType.HOME_LOAN, (byte)4, organization, "Regional Manager"));
    	
    	for(Manager manager : managers){
	    	restTemplate.put("http://localhost:8080/admin/manager/" + manager.getEmployeeId() + "/" + manager.getAppType(), manager);
	    	
    	}
	}
}
