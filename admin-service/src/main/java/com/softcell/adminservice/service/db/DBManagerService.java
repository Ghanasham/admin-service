package com.softcell.adminservice.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softcell.adminservice.domain.ApplicationType;
import com.softcell.adminservice.domain.Manager;
import com.softcell.adminservice.domain.ManagerPrimaryKey;
import com.softcell.adminservice.repo.ManagerRepository;
import com.softcell.adminservice.repo.db.ManagerMaxLevelResponse;
import com.softcell.adminservice.service.ManagerService;

@Service
public class DBManagerService implements ManagerService{

	@Autowired
	ManagerRepository managerRepo; 
	
	@Override
	public Manager getManager(ManagerPrimaryKey key) {
		return managerRepo.getManager(key);
	}

	@Override
	public Manager saveManager(Manager maanger) {
		return managerRepo.updateManager(maanger);
		
	}

	@Override
	public void deleteManager(ManagerPrimaryKey key) {
		managerRepo.deleteManager(key);
		
	}

	@Override
	public ManagerMaxLevelResponse getNextManager(Long orgId, ApplicationType appType, byte level) {
		return managerRepo.getNextManager(orgId, appType, level);
	}

}
