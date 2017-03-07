package com.softcell.adminservice.service;

import com.softcell.adminservice.domain.Manager;
import com.softcell.adminservice.domain.ManagerPrimaryKey;

public interface ManagerService {

	public Manager getManager(ManagerPrimaryKey key);
	
	public void createManager(Manager maanger);
	
	public void updateManager(Manager maanger);
	
	public void deleteManager(ManagerPrimaryKey key);
}
