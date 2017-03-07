package com.softcell.adminservice.service.db;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softcell.adminservice.dao.db.DBOrganizationDAO;
import com.softcell.adminservice.domain.Organization;
import com.softcell.adminservice.service.OrganizationService;

@Service
public class DBOrganizationService implements OrganizationService{
	
	@Autowired
	private DBOrganizationDAO organizationDAO;
	
	@Override
	public Organization getOrganization(Long orgId){
		return organizationDAO.getOrganization(orgId);
	}
	
	@Override
	public Organization createOrganization(Organization org){
		return organizationDAO.createOrganization(org);
	}
	
	@Override
	public void updateOrganization(Organization org){
		organizationDAO.updateOrganization(org);
	}
}
