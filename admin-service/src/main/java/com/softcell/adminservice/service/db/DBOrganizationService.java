package com.softcell.adminservice.service.db;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softcell.adminservice.dao.OrganizationRepository;
import com.softcell.adminservice.domain.Organization;
import com.softcell.adminservice.service.OrganizationService;

@Service
public class DBOrganizationService implements OrganizationService{
	
	@Autowired
	private OrganizationRepository organizationRepo;
	
	@Override
	public Organization getOrganization(Long orgId){
		return organizationRepo.findOne(orgId);
	}
	
	@Override
	public Organization saveOrganization(Organization org){
		return organizationRepo.save(org);
	}
	
}
