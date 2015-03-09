package com.cmcltd.flexfield.data.jpa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity(name = "ENTITYMASTER")
@Table(name = "ENTITYMASTER")
public class EntityMaster extends AbstractEntity {

	private static final Logger logger = LoggerFactory
			.getLogger(EntityMaster.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ENTITYID_GENERATOR", sequenceName = "ENTITY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENTITYID_GENERATOR")
	@Column(name = "ENTITYID")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "NAME")
	private String entityName;

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityName() {
		return entityName;
	}

	@Column(name = "DESCRIPTION")
	private String entityDesc;

	public void setEntityDesc(String entityDesc) {
		this.entityDesc = entityDesc;
	}

	public String getEntityDesc() {
		return entityDesc;
	}

	public EntityMaster(Integer id) {
		logger.trace("EntityMaster instance created");
		this.id = id;
	}

	public EntityMaster() {

		logger.trace("EntityMaster instance created");

	}

	public EntityMaster(Integer id, String name, String desc) {
		this.id = id;
		this.entityName = name;
		this.entityDesc = desc;
		logger.trace("EntityMaster instance created");
	}

	@ManyToMany
	@JoinTable(name = "ENTITYFIELDMAP", joinColumns = @JoinColumn(name = "ENTITYID"), inverseJoinColumns = @JoinColumn(name = "FIELDID"))
	private List<CustomField> customFields;

	public List<CustomField> getCustomField() {
		return this.customFields;
	}

	public void setCustomField(List<CustomField> customFields) {
		this.customFields = customFields;

	}

	public boolean add(CustomField customField) {
		if (customFields == null)
			customFields = new ArrayList<CustomField>();
		return customFields.add(customField);
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((entityName == null) ? 0 : entityName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((entityDesc == null) ? 0 : entityDesc.hashCode());
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
		if (!(obj instanceof EntityMaster)) {
			return false;
		}
		EntityMaster other = (EntityMaster) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (entityName == null) {
			if (other.entityName != null) {
				return false;
			}
		} else if (!entityName.equals(other.entityName)) {
			return false;
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
		return "EntityMaster [ID=" + id + ", Name=" + entityName
				+ ", Description =" + entityDesc + "]";

	}

}
