package com.softcell.adminservice.service;

import com.softcell.adminservice.domain.Organization;

public interface OrganizationService {

	public Organization getOrganization(Long orgId);
	
	public Organization saveOrganization(Organization org);
	
}
