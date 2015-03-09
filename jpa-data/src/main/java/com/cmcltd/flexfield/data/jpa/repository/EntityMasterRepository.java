package com.cmcltd.flexfield.data.jpa.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import  com.cmcltd.flexfield.data.jpa.domain.EntityMaster;

/**
 * A DAO for the entity User is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 * The magic is that such methods must not be implemented, and moreover it is
 * possible create new query methods working only by defining their signature!
 * 
 * @author CMC Limited
 */
@Transactional
public interface EntityMasterRepository extends CrudRepository<EntityMaster, Long> {

	/**
	 * Return the Entity .
	 * 
	 * @param Name
	 *            the Entity's Name.
	 */
	@Query("select em from ENTITYMASTER em where em.entityName = ?1")
	public EntityMaster findByName(String name);

	@Query("select em from ENTITYMASTER em where em.entityName = ?1")
	public EntityMaster findEntityByName(String name);

}
