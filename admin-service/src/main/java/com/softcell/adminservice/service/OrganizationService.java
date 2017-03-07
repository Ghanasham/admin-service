package com.softcell.adminservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softcell.adminservice.dao.OrganizationDAO;
import com.softcell.adminservice.domain.Organization;

@Service
public class OrganizationService {
	
	@Autowired
	private OrganizationDAO organizationDAO;
	
	
	public Organization getOrganization(Long orgId){
		return organizationDAO.getOrganization(orgId);
	}
	
	public void createOrganization(Organization org){
		organizationDAO.createOrganization(org);
	}
	
	public void updateOrganization(Organization org){
		organizationDAO.updateOrganization(org);
	}
}
