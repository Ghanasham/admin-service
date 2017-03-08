package com.softcell.adminservice.domain;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Organization {

	@Id
	private Long orgId;
	
	@Column(nullable=false)
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name="contact_details_id")
	private ContactDetails orgContactDetails;
	
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

	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Organization))
			return false;
		
		Organization other = (Organization)obj;
		return this.orgId.equals(other.getOrgId())
				&& this.name.equals(other.getName());
	}
		
}
