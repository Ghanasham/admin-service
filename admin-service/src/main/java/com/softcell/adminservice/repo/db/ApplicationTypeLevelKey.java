package com.softcell.adminservice.repo.db;

import com.softcell.adminservice.domain.ApplicationType;

public final class ApplicationTypeLevelKey {

	private final Long orgId;
	
	private final ApplicationType appType;

	public ApplicationTypeLevelKey(Long orgId, ApplicationType appType){
		this.orgId = orgId;
		this.appType = appType;
	}

	public Long getOrgId() {
		return orgId;
	}

	public ApplicationType getAppType() {
		return appType;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("This is immutable object");
	}
}