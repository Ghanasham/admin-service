package com.softcell.adminservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softcell.adminservice.domain.ApplicationType;
import com.softcell.adminservice.domain.Manager;
import com.softcell.adminservice.domain.ManagerPrimaryKey;
import com.softcell.adminservice.repo.db.ManagerMaxLevelResponse;
import com.softcell.adminservice.service.ManagerService;

@RestController
@RequestMapping(path = "/admin/manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@RequestMapping(path = "/{employeeId}/{appType}", method = RequestMethod.GET)
	public Manager getManager(@PathVariable Long employeeId, @PathVariable ApplicationType appType){
		return managerService.getManager(new ManagerPrimaryKey(employeeId, appType));
	}
	
	@RequestMapping(path = "/next-manager/{orgId}/{appType}/{level}", method = RequestMethod.GET)
	public ManagerMaxLevelResponse getNextManagerId(@PathVariable Long orgId, @PathVariable ApplicationType appType, @PathVariable byte level){
		return managerService.getNextManager(orgId, appType, level);
	}
	
	@RequestMapping(path = "/{employeeId}/{appType}", method = RequestMethod.PUT)
	public HttpStatus updateManager(@PathVariable Long employeeId, @PathVariable ApplicationType appType, @RequestBody Manager manager){
		managerService.saveManager(manager);
		return HttpStatus.OK;
	}
	
	@RequestMapping(path = "/{employeeId}/{appType}", method = RequestMethod.DELETE)
	public void deleteManager(@PathVariable Long employeeId, @PathVariable ApplicationType appType){
		managerService.deleteManager(new ManagerPrimaryKey(employeeId, appType));
	}
}
