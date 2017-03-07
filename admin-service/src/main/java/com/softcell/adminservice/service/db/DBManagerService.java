package com.softcell.adminservice.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softcell.adminservice.dao.ManagerDAO;
import com.softcell.adminservice.domain.Manager;
import com.softcell.adminservice.domain.ManagerPrimaryKey;
import com.softcell.adminservice.service.ManagerService;

@Service
public class DBManagerService implements ManagerService{

	@Autowired
	ManagerDAO managerDAO; 
	
	@Override
	public Manager getManager(ManagerPrimaryKey key) {
		return managerDAO.getManager(key);
	}

	@Override
	public void createManager(Manager maanger) {
		managerDAO.createManager(maanger);
		
	}

	@Override
	public void updateManager(Manager maanger) {
		managerDAO.updateManager(maanger);
		
	}

	@Override
	public void deleteManager(ManagerPrimaryKey key) {
		managerDAO.deleteManager(key);
		
	}

}
