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

@Entity(name = "CUSTOMFIELDDATA")
@Table(name = "CUSTOMFIELDDATA")
public class EntityCustomFieldData extends AbstractEntity{
	
	private static final Logger logger = LoggerFactory.getLogger(EntityCustomFieldData.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "CUSTOMFIELDIDDATA_GEN", sequenceName = "CUSTOMFIELDDATA_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMFIELDIDDATA_GEN")
	@Column(name = "CUSTOMFIELDDATAID")
	private Integer id;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Creates a new {@link EntityCustomFieldData} for the given Entity and
	 * collection of Custom Fields {@link CustomField}.
	 * 
	 * @param Entity
	 *            Name must not be {@literal null}.
	 * @param CustomField
	 *            must not be {@literal null}.
	 * 
	 */

	@Column(name = "MAPID")
	private Integer mapId;

	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	public Integer getMapId() {
		return this.mapId;
	}

	@Column(name = "ENTITYTXNID")
	private String entityTxnId;

	public void setEntityTxnId(String entityTxnId) {
		this.entityTxnId = entityTxnId;
	}

	public String getEntityTxnId() {
		return entityTxnId;
	}

	@Column(name = "VALUE")
	private String value;

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
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
	

	public EntityCustomFieldData( Integer mapId, String txnId,
			String value) {
		logger.trace("EntityCustomFieldData instance created");
		Assert.notNull(txnId, "Entity Txn ID cannot be NULL.");
		
		this.mapId = mapId;
		this.entityTxnId = txnId;
		this.value = value;
	}

	public EntityCustomFieldData(Integer id) {
		this.id = id;
		logger.trace("EntityCustomFieldData instance created");
	}

	public EntityCustomFieldData() {
		logger.trace("EntityCustomFieldData instance created");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((entityTxnId == null) ? 0 : entityTxnId.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof EntityCustomFieldData)) {
			return false;
		}
		EntityCustomFieldData other = (EntityCustomFieldData) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}

		if (mapId == null) {
			if (other.mapId != null) {
				return false;
			} else if (!mapId.equals(other.mapId)) {
				return false;
			}
		}

		if (entityTxnId == null) {
			if (other.entityTxnId != null) {
				return false;
			} else if (!entityTxnId.equals(other.entityTxnId)) {
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
		return "EntityCustomFieldData [ID=" + id + ", Map Id=" + mapId
				+ ", Txn ID=" + entityTxnId + ", Value =" + value + "]";

	}

}
