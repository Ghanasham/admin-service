package com.softcell.adminservice.dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softcell.adminservice.domain.Organization;

@Repository
public class OrganizationDAO {

	@Autowired
	EntityManagerFactory emFactory;
	
	public Organization getOrganization(Long orgId){
		EntityManager manager = emFactory.createEntityManager();
		
		manager.getTransaction().begin();
		
		Organization org = manager.find(Organization.class, orgId);
		
		manager.getTransaction().commit();
		manager.close();
		
		return org;
		
	}
	
	public void createOrganization(Organization org){
		EntityManager manager = emFactory.createEntityManager();
		manager.getTransaction().begin();
		
		manager.persist(org);
		
		manager.getTransaction().commit();
		manager.close();
		
	}
	
	public void updateOrganization(Organization org){
		EntityManager manager = emFactory.createEntityManager();
		manager.getTransaction().begin();
		
		Organization dbOrg = manager.find(Organization.class, org.getOrgId());
		if(dbOrg == null)
			throw new RuntimeException("No organization found for the given id");
		
		dbOrg.setName(org.getName());
		dbOrg.setOrgContactDetails(org.getOrgContactDetails());
		
		manager.getTransaction().commit();
		manager.close();
	}
	
	
}
