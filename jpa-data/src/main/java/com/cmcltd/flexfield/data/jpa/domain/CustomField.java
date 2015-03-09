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
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity(name = "CUSTOMFIELD")
@Table(name = "CUSTOMFIELD")
public class CustomField extends AbstractEntity {

	private static final Logger logger = LoggerFactory.getLogger(CustomField.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "FIELDID_GEN", sequenceName = "CUSTOMFIELD_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FIELDID_GEN")
	@Column(name = "FIELDID")
	private Integer id;

	public Integer getId() {
		return this.id;
	}

	@Column(name = "DESCRIPTION")
	private String desc;

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	@Column(name = "FIELDNAME")
	private String fieldName;

	public void setfieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	@Column(name = "TYPE")
	private String fieldType;

	public String getFieldType() {
		return fieldType;
	}

	public void setfieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	@Column(name = "LENGTH")
	private Integer fieldLength;

	public Integer getFieldLength() {
		return fieldLength;
	}

	public void setfieldLength(Integer fieldLength) {
		this.fieldLength = fieldLength;
	}

	@Column(name = "ISACTIVE")
	private String isActive;

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Transient
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String val) {
		this.value = val;
	}
	
	@Transient
	private Integer mapId;

	public Integer getMapId() {
		return mapId;
	}

	public void setMapId(Integer mapid) {
		this.mapId = mapid;
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

	public CustomField() {
		logger.trace("CustomField instance created");
	}

	public CustomField(Integer id) {
		logger.trace("CustomField instance created");
		this.id = id;
	}
	
	public CustomField(CustomField cf){
		this.id = cf.getId();
		this.fieldName = cf.getFieldName();
		this.fieldType = cf.getFieldType();
		this.fieldLength = cf.getFieldLength();
		this.isActive = cf.getIsActive();
		this.value = cf.getValue();
		this.mapId = cf.getMapId();
		
	}

	public CustomField(Integer id, String fieldName, String desc, String type,
			Integer length, String isActive) {
		logger.trace("CustomField instance created");
		this.id = id;
		this.fieldName = fieldName;
		this.desc = desc;
		this.fieldType = type;
		this.fieldLength = length;
		this.isActive = isActive;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fieldName == null) ? 0 : fieldName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((fieldType == null) ? 0 : fieldType.hashCode());
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
		if (!(obj instanceof CustomField)) {
			return false;
		}
		CustomField other = (CustomField) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (fieldName == null) {
			if (other.fieldName != null) {
				return false;
			}
		} else if (!fieldName.equals(other.fieldName)) {
			return false;
		}
		if(!this.value.equals(other.value)){
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
		return "Custom Field [ID=" + id + ", Name=" + fieldName + ", Type="
				+ fieldType + " , Length=" + fieldLength + ", Field value ="
				+ value + "]";

	}

}
