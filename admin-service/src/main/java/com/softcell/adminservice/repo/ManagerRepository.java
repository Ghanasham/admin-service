package com.softcell.adminservice.repo;

import com.softcell.adminservice.domain.ApplicationType;
import com.softcell.adminservice.domain.Manager;
import com.softcell.adminservice.domain.ManagerPrimaryKey;


public interface ManagerRepository{

	public Manager getManager(ManagerPrimaryKey id);
	
	/**
	 * Returns next manager for the given parameters based on Round Robin strategy
	 * @param orgId
	 * @param appType
	 * @param level
	 * @return next manager
	 */
	public Manager getNextManager(Long orgId, ApplicationType appType, byte level);
	
	public Manager updateManager(Manager manager);
	
	public void deleteManager(ManagerPrimaryKey id);
}
