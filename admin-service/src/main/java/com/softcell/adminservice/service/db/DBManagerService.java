package com.softcell.adminservice.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softcell.adminservice.dao.ManagerRepository;
import com.softcell.adminservice.domain.Manager;
import com.softcell.adminservice.domain.ManagerPrimaryKey;
import com.softcell.adminservice.service.ManagerService;

@Service
public class DBManagerService implements ManagerService{

	@Autowired
	ManagerRepository managerRepo; 
	
	@Override
	public Manager getManager(ManagerPrimaryKey key) {
		return managerRepo.findOne(key);
	}

	@Override
	public Manager saveManager(Manager maanger) {
		return managerRepo.save(maanger);
		
	}

	@Override
	public void deleteManager(ManagerPrimaryKey key) {
		managerRepo.delete(key);
		
	}

}
