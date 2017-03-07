package com.softcell.adminservice.dao;

import com.softcell.adminservice.domain.Organization;

public interface OrganizationDAO {

	public Organization getOrganization(Long orgId);
	
	public Organization createOrganization(Organization org);
	
	public void updateOrganization(Organization org);
	
}
