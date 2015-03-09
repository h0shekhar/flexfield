package com.cmcltd.flexfield.data.jpa.domain;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

@Entity(name = "ENTITYFIELDMAP")
@Table(name = "ENTITYFIELDMAP")
public class EntityCustomField extends AbstractEntity {
	
	private static final Logger logger = LoggerFactory.getLogger(EntityCustomField.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ENTITYFIELDID_GEN", sequenceName = "ENTITYFIELDMAP_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENTITYFIELDID_GEN")
	@Column(name = "MAPID")
	private Integer id;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Creates a new {@link EntityCustomField} for the given Entity and
	 * collection of Custom Fields {@link CustomField}.
	 * 
	 * @param Entity
	 *            Name must not be {@literal null}.
	 * @param CustomField
	 *            must not be {@literal null}.
	 * 
	 */

	@Column(name = "ENTITYID")
	private Integer entityId;

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	public Integer getEntityId() {
		return entityId;
	}

	@Column(name = "FIELDID")
	private Integer fieldId;

	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}

	public Integer getFieldId() {
		return fieldId;
	}

	private String isActive;

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Basic(fetch = FetchType.LAZY)
	@Column(name = "CREATED_DATETM")
	private Date createdDateTm;

	public Date getCreatedDateTm() {
		return createdDateTm;
	}

	public void setCreatedDateTm(Date createdDateTm) {
		this.createdDateTm = createdDateTm;
	}

	@Basic(fetch = FetchType.LAZY)
	@Column(name = "UPDATED_DATETM")
	private Date updatedDateTm;

	public Date getUpdatedDateTm() {
		return updatedDateTm;
	}

	public void setUpdatedDateTm(Date updatedDateTm) {
		this.updatedDateTm = updatedDateTm;
	}

	public EntityCustomField(Integer id, Integer entityId, Integer fieldId,
			String isActive) {

		Assert.notNull(entityId, "Entity ID cannot be NULL");

		this.id = id;
		this.entityId = entityId;
		this.fieldId = fieldId;
		this.isActive = isActive;
		logger.trace("EntityCustomField instance created");
	}

	public EntityCustomField(Integer id) {
		this.id = id;
		logger.trace("EntityCustomField instance created");
	}
	
	public EntityCustomField() {
		logger.trace("EntityCustomField instance created");		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((entityId == null) ? 0 : entityId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((fieldId == null) ? 0 : fieldId.hashCode());

		return result;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof EntityCustomField)) {
			return false;
		}
		EntityCustomField other = (EntityCustomField) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!entityId.equals(other.entityId)) {
			return false;
		}
		if (fieldId == null) {
			if (other.fieldId != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EntityCustomField [ID=" + id + ", Entity Id=" + entityId
				+ ", Field ID=" + fieldId + "]";

	}

}
