package com.softcell.adminservice.domain;

import java.io.Serializable;

public final class ManagerPrimaryKey implements Serializable{

	private static final long serialVersionUID = 1L;

	public Long employeeId;
	
	public ApplicationType appType;
	
	public ManagerPrimaryKey(){}
	
	public ManagerPrimaryKey(Long employeeId, ApplicationType appType){
		this.employeeId = employeeId;
		this.appType = appType;
	}
	
	@Override
	public boolean equals(Object obj) {

		if(obj == null || !(obj instanceof ManagerPrimaryKey))
			return false;
		
		ManagerPrimaryKey key = (ManagerPrimaryKey)obj;
		
		return (this.employeeId == null ? key.employeeId == null : this.employeeId.equals(key.employeeId)
				&& this.appType == null ? key.appType == null : this.appType.equals(key.appType));
		
	}
	@Override
	public int hashCode() {
		return ((employeeId == null ? 0 : employeeId.hashCode()
				^ (appType == null ? 0 : appType.hashCode())));
	}
	
	
}
