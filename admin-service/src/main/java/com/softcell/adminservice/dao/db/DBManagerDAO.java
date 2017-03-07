package com.softcell.adminservice.dao.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softcell.adminservice.dao.ManagerDAO;
import com.softcell.adminservice.domain.Manager;
import com.softcell.adminservice.domain.ManagerPrimaryKey;

@Repository
public class DBManagerDAO implements ManagerDAO{

	@Autowired
	EntityManagerFactory emFactory;
	
	@Override
	public Manager getManager(ManagerPrimaryKey key){
		EntityManager entityManager = emFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		
		Manager manager = entityManager.find(Manager.class, key);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		return manager;
		
	}
	
	@Override
	public void createManager(Manager manager){
		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.persist(manager);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
	
	@Override
	public void updateManager(Manager manager){
		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Manager dbManager = entityManager.find(Manager.class, new ManagerPrimaryKey(manager.getEmployeeId(), manager.getAppType()));
		if(dbManager == null)
			entityManager.persist(manager);
		else{
			dbManager.setLevel(manager.getLevel());
			dbManager.setRole(manager.getRole());
			
		}
		
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	@Override
	public void deleteManager(ManagerPrimaryKey key){
		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Manager dbManager = entityManager.find(Manager.class, key);
		if(dbManager != null)
			entityManager.remove(dbManager);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
}
