package com.softcell.adminservice.service;

import com.softcell.adminservice.domain.ApplicationType;
import com.softcell.adminservice.domain.Manager;
import com.softcell.adminservice.domain.ManagerPrimaryKey;
import com.softcell.adminservice.repo.db.ManagerMaxLevelResponse;

public interface ManagerService {

	public Manager getManager(ManagerPrimaryKey key);
	
	/**
	 * Returns next manager for the given parameters based on Round Robin strategy
	 * @param orgId
	 * @param appType
	 * @param level
	 * @return next manager
	 */
	public ManagerMaxLevelResponse getNextManager(Long orgId, ApplicationType appType, byte level);

	
	public Manager saveManager(Manager maanger);
	
	public void deleteManager(ManagerPrimaryKey key);
}
