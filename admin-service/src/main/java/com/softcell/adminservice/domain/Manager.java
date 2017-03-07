package com.softcell.adminservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@Column(name="org_id", nullable=false,
    insertable=false, updatable=false)
	@JoinColumn(referencedColumnName="org_id")
	private Long orgId;
	
	@Id
	private ApplicationType appType;
	
	private String role;
	
	private byte level;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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
}
