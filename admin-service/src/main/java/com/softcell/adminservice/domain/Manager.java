package com.softcell.adminservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

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
	
	@Column(nullable = false)
	private byte level;
	
	/**
	 * Reference of next manager in circular linked list
	 */
	@Transient
	Manager next;
	
	public Manager(){}
	
	public Manager(Long employeeId, ApplicationType appType, byte level, Organization org, String role){
		this.employeeId = employeeId;
    	this.appType = appType;
    	this.level = level;
    	this.orgId = org;
    	this.role = role;
	}

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
