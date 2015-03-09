package com.cmcltd.flexfield.data.jpa.repository;

import java.util.List;


//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cmcltd.flexfield.data.jpa.domain.EntityCustomField;

 
public interface EntityCustomFieldRepository extends CrudRepository<EntityCustomField, Long> {
	
	
	@Query("select e from ENTITYFIELDMAP e where e.fieldId = ?1")
	List<EntityCustomField> findByFieldId(Integer fieldId);
	
	@Query("select e from ENTITYFIELDMAP e where e.entityId = ?1")
	List<EntityCustomField> findByEntityId(Integer entityId);
	
	@Query("select e.id from ENTITYFIELDMAP e where e.entityId = ?1 and e.fieldId = ?2")
	Integer findByEntityidAndFieldid(Integer eid, Integer fid);
}
