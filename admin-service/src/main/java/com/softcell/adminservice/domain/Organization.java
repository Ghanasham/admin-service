package com.softcell.adminservice.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Organization {

	@Id
	@GeneratedValue
	private Long orgId;
	
	@Column(nullable=false)
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name="contact_details_id")
	private ContactDetails orgContactDetails;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<Manager> managers;
	
	public Organization(){}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public ContactDetails getOrgContactDetails() {
		return orgContactDetails;
	}

	public void setOrgContactDetails(ContactDetails orgContactDetails) {
		this.orgContactDetails = orgContactDetails;
	}

	public Set<Manager> getManagers() {
		return managers;
	}

	public void setManagers(Set<Manager> managers) {
		if(this.managers == null)
			this.managers = managers;
		else{
			this.managers.clear();
			this.managers.addAll(managers);
		}
			
	}
	
}
