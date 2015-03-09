package com.cmcltd.flexfield.data.jpa.domain;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * Base class to derive entity classes from.
 * 
 * @author
 * @version 0.1
 */

@MappedSuperclass
public class AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Transient private Integer id;

	/**
	 * Returns the identifier of the entity.
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/*
	 * @
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (this.id == null || obj == null
				|| !(this.getClass().equals(obj.getClass()))) {
			return false;
		}

		AbstractEntity that = (AbstractEntity) obj;

		return this.id.equals(that.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}
	
	@Override
	public String toString() {
		return "AbstractEntity : "+id.toString();
	}

}
