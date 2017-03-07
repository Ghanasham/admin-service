package com.softcell.adminservice.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@IdClass(ManagerPrimaryKey.class)
@Entity
public class Manager {

	@Id
	private Long employeeId;
	
	@Id
	private ApplicationType appType;
	
	@ManyToOne
	@JoinColumn(name="org_id")
	private Organization orgId;
	
	private String role;
	
	private byte level;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public ApplicationType getAppType() {
		return appType;
	}

	public void setAppType(ApplicationType appType) {
		this.appType = appType;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public byte getLevel() {
		return level;
	}

	public void setLevel(byte level) {
		this.level = level;
	}

	public Organization getOrgId() {
		return orgId;
	}

	public void setOrgId(Organization orgId) {
		this.orgId = orgId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Manager))
			return false;
		
		Manager other = (Manager)obj;
		return this.employeeId.equals(other.getEmployeeId())
				&& this.appType.equals(other.getAppType())
				&& this.level == other.getLevel()
				&& this.getOrgId().getOrgId().equals(other.getOrgId().getOrgId())
				&& this.role.equals(other.getRole());
	}
}
