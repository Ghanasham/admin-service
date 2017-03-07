package com.softcell.adminservice.integration;

import org.junit.Assert;
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
	public void testCreateOrUpdateManager() throws Exception {
		
		Organization organization = new Organization();
    	organization.setOrgId(1L);
    	organization.setName("HDFC");
    	
    	Manager manager = new Manager();
    	manager.setEmployeeId(12348L);
    	manager.setAppType(ApplicationType.HOME_LOAN);
    	manager.setLevel((byte)2);
    	manager.setOrgId(organization);
    	manager.setRole("Risk Manager");
    	
    	restTemplate.put("http://localhost:8080/admin/org/" 
    	+ organization.getOrgId() + "/manager/" + manager.getEmployeeId() + "/" + manager.getAppType(), manager);
    	
    	Manager dbManager = restTemplate.getForObject("http://localhost:8080/admin/org/" 
    	+ organization.getOrgId() + "/manager/" + manager.getEmployeeId() + "/" + manager.getAppType(), Manager.class);
    	
    	Assert.assertEquals(manager, dbManager);
	}

	@Test
	public void testGetManager() throws Exception {
	
		Manager dbManager = restTemplate.getForObject("http://localhost:8080/admin/org/1/manager/12345/HOME_LOAN", Manager.class);
		
		Assert.assertNotNull(dbManager);
		//Assert.assertEquals(1L, dbManager.getOrgId().getOrgId());
		//Assert.assertEquals(12345L, dbManager.getEmployeeId());
		Assert.assertEquals(ApplicationType.HOME_LOAN, dbManager.getAppType());
	}
	
	@Test
	public void testDeleteManager() throws Exception {
		
		Manager dbManager = restTemplate.getForObject("http://localhost:8080/admin/org/1/manager/12345/HOME_LOAN", Manager.class);
		
		Assert.assertNotNull(dbManager);
		
		restTemplate.delete("http://localhost:8080/admin/org/1/manager/12345/HOME_LOAN");
		
		dbManager = restTemplate.getForObject("http://localhost:8080/admin/org/1/manager/12345/HOME_LOAN", Manager.class);
		
		Assert.assertNull(dbManager);
	}
}
