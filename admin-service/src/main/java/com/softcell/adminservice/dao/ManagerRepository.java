package com.softcell.adminservice.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.softcell.adminservice.domain.Manager;
import com.softcell.adminservice.domain.ManagerPrimaryKey;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, ManagerPrimaryKey>{

}
