package com.softcell.adminservice.repo.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softcell.adminservice.domain.ApplicationType;
import com.softcell.adminservice.domain.CircularLinkedList;
import com.softcell.adminservice.domain.Manager;
import com.softcell.adminservice.domain.ManagerPrimaryKey;
import com.softcell.adminservice.repo.ManagerRepository;

@Repository
public class DBManagerRepository implements ManagerRepository{

	@Autowired
	EntityManagerFactory emFactory;
	
	private Map<OrgAppTypeLevelKey, CircularLinkedList<Manager>> managerLevelMap = new HashMap<>();
	
	private Map<OrgAppTypeKey, Byte> maxLevels = new HashMap<>();
	
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void initManagerLevelMap(){
		
		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		List<Manager> managerList = entityManager.createQuery("SELECT m FROM Manager m ORDER BY m.employeeId").getResultList();
		
		for(Manager manager : managerList){
			OrgAppTypeLevelKey key = new OrgAppTypeLevelKey(manager.getOrgId().getOrgId(), manager.getAppType(), manager.getLevel());
			
			CircularLinkedList<Manager> circularList = managerLevelMap.get(key);
			if(circularList == null){
				circularList = new CircularLinkedList<>();
				managerLevelMap.put(key, circularList);
			}
			circularList.add(manager);
			
		}
		
		List<Object[]> results = entityManager.createQuery("SELECT m.orgId.orgId, m.appType, MAX(m.level) FROM Manager m GROUP BY m.orgId, m.appType").getResultList();
		
		for (Object[] result : results) {
			
			maxLevels.put(new OrgAppTypeKey((Long) result[0], (ApplicationType) result[1]), (Byte) result[2]);
		}
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	/**
	 * Returns next manager for the given parameters based on Round Robin strategy
	 * @param orgId
	 * @param appType
	 * @param level
	 * @return next manager
	 */
	@Override
	public ManagerMaxLevelResponse getNextManager(Long orgId, ApplicationType appType, byte level){
		
		OrgAppTypeLevelKey key1 = new OrgAppTypeLevelKey(orgId, appType, level);
		Long managerEmployeeId = managerLevelMap.get(key1).getNext().getEmployeeId();
		
		
		OrgAppTypeKey key2 = new OrgAppTypeKey(orgId, appType);
		Byte maxLevel = maxLevels.get(key2);
		
		System.out.println("managerEmployeeId - " + managerEmployeeId + " maxLevel - " + maxLevel);
		
		return new ManagerMaxLevelResponse(managerEmployeeId, maxLevel);
	}
	
	@Override
	public Manager getManager(ManagerPrimaryKey id) {
		
		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Manager manager = entityManager.find(Manager.class, id);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return manager;
	}

	@Override
	public Manager updateManager(Manager manager) {
		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(manager);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return manager;
		
		//ToDo
		//Update in managerLevelMap
	}

	@Override
	public void deleteManager(ManagerPrimaryKey id) {
		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.remove(id);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		
		//ToDo
		//Delete from managerLevelMap
		
	}

}
