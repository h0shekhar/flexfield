package com.cmcltd.flexfield.data.jpa.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cmcltd.flexfield.data.jpa.domain.EntityCustomFieldData;



public interface EntityCustomFieldDataRepository extends CrudRepository<EntityCustomFieldData, Long> {
	
	
	@Query("select d from CUSTOMFIELDDATA d where d.mapId = ?1 and d.entityTxnId = ?2")
	EntityCustomFieldData findByMapAndTxnId(Integer entityId, String txnId);

	@Query("select d.value from CUSTOMFIELDDATA d where d.mapId = ?1 and d.entityTxnId = ?2")
	String findValueByMapAndTxnId(Integer entityId, String txnId);
	
	@Query("select d from CUSTOMFIELDDATA d where d.entityTxnId = ?2")
	Set<EntityCustomFieldData> findByTxnId(String txnId);
}
