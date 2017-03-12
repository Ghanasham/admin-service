package com.softcell.adminservice.repo.db;

public class ManagerMaxLevelResponse {

	private Long managerEmployeeId;
	
	private Byte maxLevels;
	
	public ManagerMaxLevelResponse(){}
	
	public ManagerMaxLevelResponse(Long managerEmployeeId, Byte maxLevels){
		this.managerEmployeeId = managerEmployeeId;
		this.maxLevels = maxLevels;
	}

	public Long getManagerEmployeeId() {
		return managerEmployeeId;
	}

	public void setManagerEmployeeId(Long managerEmployeeId) {
		this.managerEmployeeId = managerEmployeeId;
	}

	public Byte getMaxLevels() {
		return maxLevels;
	}

	public void setMaxLevels(Byte maxLevels) {
		this.maxLevels = maxLevels;
	}
	
}
