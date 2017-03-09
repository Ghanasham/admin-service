package com.softcell.adminservice.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.softcell.adminservice.domain.Organization;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Long>{

}
