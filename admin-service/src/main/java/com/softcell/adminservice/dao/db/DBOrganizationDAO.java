package com.softcell.adminservice.dao.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softcell.adminservice.dao.OrganizationDAO;
import com.softcell.adminservice.domain.Organization;

@Repository
public class DBOrganizationDAO implements OrganizationDAO{

	@Autowired
	EntityManagerFactory emFactory;
	
	@Override
	public Organization getOrganization(Long orgId){
		EntityManager manager = emFactory.createEntityManager();
		
		manager.getTransaction().begin();
		
		Organization org = manager.find(Organization.class, orgId);
		
		manager.getTransaction().commit();
		manager.close();
		
		return org;
		
	}
	
	@Override
	public Organization createOrganization(Organization org){
		EntityManager manager = emFactory.createEntityManager();
		manager.getTransaction().begin();
		
		manager.persist(org);
		
		manager.getTransaction().commit();
		manager.close();
		return org;
	}
	
	@Override
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
