package com.softcell.adminservice.service;

import com.softcell.adminservice.domain.Manager;
import com.softcell.adminservice.domain.ManagerPrimaryKey;

public interface ManagerService {

	public Manager getManager(ManagerPrimaryKey key);
	
	public Manager saveManager(Manager maanger);
	
	public void deleteManager(ManagerPrimaryKey key);
}
