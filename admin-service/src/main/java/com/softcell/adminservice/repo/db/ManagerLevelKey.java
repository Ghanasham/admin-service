package com.softcell.adminservice.repo.db;

import com.softcell.adminservice.domain.ApplicationType;

/**
 * Immutable key to identify a Manager object for a given orgId, applicationType and level.
 * @author Ghanasham
 *
 */
public final class ManagerLevelKey {

	private Long orgId;
	
	private ApplicationType applicationType;
	
	private byte level;
	
	@SuppressWarnings("unused")
	private ManagerLevelKey(){}
	
	public ManagerLevelKey(Long orgId, ApplicationType applicationType, byte level){
		
		this.orgId = orgId;
		this.applicationType = applicationType;
		this.level = level;
	}

	public Long getOrgId() {
		return orgId;
	}

	public ApplicationType getApplicationType() {
		return applicationType;
	}

	public byte getLevel() {
		return level;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("This is immutable object");
	}
}
