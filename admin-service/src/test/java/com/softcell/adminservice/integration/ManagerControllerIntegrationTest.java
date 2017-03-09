package com.softcell.adminservice.integration;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.softcell.adminservice.domain.ApplicationType;
import com.softcell.adminservice.domain.Manager;
import com.softcell.adminservice.domain.Organization;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ManagerControllerIntegrationTest {

	@Autowired
    private TestRestTemplate restTemplate;

	@Test
	public void testCreateOrUpdateManagers() throws Exception {
		
		Organization organization = new Organization();
    	organization.setOrgId(1L);
    	organization.setName("HDFC");
    	
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
	    	
	    	Manager dbManager = restTemplate.getForObject("http://localhost:8080/admin/manager/" + manager.getEmployeeId() + "/" + manager.getAppType(), Manager.class);
	    	
	    	Assert.assertEquals(manager, dbManager);
    	}
	}

	@Test
	@Ignore
	public void testGetManager() throws Exception {
	
		Manager dbManager = restTemplate.getForObject("http://localhost:8080/admin/manager/12345/HOME_LOAN", Manager.class);
		
		Assert.assertNotNull(dbManager);
		
	}
	
	@Test
	@Ignore
	public void testDeleteManager() throws Exception {
		
		Manager dbManager = restTemplate.getForObject("http://localhost:8080/admin/manager/12345/HOME_LOAN", Manager.class);
		
		Assert.assertNotNull(dbManager);
		
		restTemplate.delete("http://localhost:8080/admin/manager/12345/HOME_LOAN");
		
		dbManager = restTemplate.getForObject("http://localhost:8080/admin/manager/12345/HOME_LOAN", Manager.class);
		
		Assert.assertNull(dbManager);
	}
	
	
}
