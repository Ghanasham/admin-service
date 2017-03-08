package com.softcell.adminservice.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.softcell.adminservice.domain.Organization;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Long>{

}
