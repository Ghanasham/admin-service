package com.softcell.adminservice.service;

import com.softcell.adminservice.domain.Organization;

public interface OrganizationService {

	public Organization getOrganization(Long orgId);
	
	public Organization createOrganization(Organization org);
	
	public void updateOrganization(Organization org);
}
