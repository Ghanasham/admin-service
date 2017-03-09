package com.softcell.adminservice.repo.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softcell.adminservice.domain.ApplicationType;
import com.softcell.adminservice.domain.Manager;
import com.softcell.adminservice.domain.ManagerCircularLinkedList;
import com.softcell.adminservice.domain.ManagerPrimaryKey;
import com.softcell.adminservice.repo.ManagerRepository;

@Repository
public class DBManagerRepository implements ManagerRepository{

	@PersistenceUnit
	EntityManagerFactory emFactory;
	
	private Map<ManagerLevelKey, ManagerCircularLinkedList> managerLevelMap;
	
	private Map<ApplicationTypeLevelKey, Byte> maxLevels;
	
	public DBManagerRepository(){
		initManagerLevelMap();
	}
	
	private void initManagerLevelMap(){
		managerLevelMap = new HashMap<>();
		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		@SuppressWarnings("unchecked")
		List<Manager> managerList = entityManager.createQuery("SELECT m FROM Manager m").getResultList();
		
		
		for(Manager manager : managerList){
			ManagerLevelKey key = new ManagerLevelKey(manager.getOrgId().getOrgId(), manager.getAppType(), manager.getLevel());
			
			ManagerCircularLinkedList circularList = managerLevelMap.get(key);
			if(circularList == null){
				circularList = new ManagerCircularLinkedList();
			}
			circularList.add(manager);
		}
	}
	
	/**
	 * Returns next manager for the given parameters based on Round Robin strategy
	 * @param orgId
	 * @param appType
	 * @param level
	 * @return next manager
	 */
	@Override
	public Manager getNextManager(Long orgId, ApplicationType appType, byte level){
		
		ManagerLevelKey key = new ManagerLevelKey(orgId, appType, level);
		return managerLevelMap.get(key).getNext();
		
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
