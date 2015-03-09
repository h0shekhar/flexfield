package com.cmcltd.flexfield.data.jpa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * Base class to derive entity classes from.
 * 
 * @author
 * @version 0.1
 */

@MappedSuperclass
public class BaseBusinessEntity extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	private String entityName;

	public void setEntityName(String name) {
		this.entityName = name;
	}

	public String getEntityName() {
		return this.entityName;
	}

	public String toString() {

		return "BaseBusinessEntity";
	}
	
	@Transient
	private List<CustomField> customField;

	public void setCustomField(List<CustomField> cf) {
		this.customField = cf;
	}

	public List<CustomField> getCustomField() {
		return this.customField;
	}
	
	public void addCustomField(List<CustomField> cfs) {
		customField = new ArrayList<CustomField>();
		customField.addAll(cfs);
	}

}
