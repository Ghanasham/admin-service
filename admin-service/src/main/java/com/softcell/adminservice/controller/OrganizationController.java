package com.softcell.adminservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softcell.adminservice.domain.Organization;
import com.softcell.adminservice.service.OrganizationService;

@RestController
@RequestMapping(path = "/admin/org")
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;

	@RequestMapping(path = "/{orgId}", method = RequestMethod.GET)
	public Organization getOrganization(@PathVariable Long orgId){
		return organizationService.getOrganization(orgId);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public HttpStatus createOrganization(@RequestBody Organization org){
		organizationService.createOrganization(org);
		return HttpStatus.OK;
	}
	
	@RequestMapping(path = "/{orgId}", method = RequestMethod.PUT)
	public HttpStatus updateOrganization(@PathVariable Long orgId, @RequestBody Organization org){
		org.setOrgId(orgId);
		organizationService.updateOrganization(org);
		return HttpStatus.OK;
	}
}
