package com.cmcltd.flexfield.data.jpa.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity(name = "DEPT")
@Table(name = "DEPT")
public class Dept extends BaseBusinessEntity {
	private static final Logger logger = LoggerFactory.getLogger(Dept.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DEPTNO", nullable = false, length = 2)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer deptNo) {
		this.id = deptNo;
	}

	@Column(name = "DNAME", nullable = false)
	private String deptName;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "LOC", nullable = false)
	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String loc) {
		this.location = loc;
	}

	public Dept(Integer deptNo, String deptName, String location) {
		this.id = deptNo;
		this.deptName = deptName;
		this.location = location;
		setEntityName("DEPT");
		logger.trace("Dept instance created");
	}

	public Dept(Integer deptNo) {
		setEntityName("DEPT");
		this.id = deptNo;
		logger.trace("Dept instance created");
	}

	public Dept() {
		setEntityName("DEPT");
		logger.trace("Dept instance created");
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		result = prime * result
				+ ((deptName == null) ? 0 : deptName.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
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
		if (!(obj instanceof Dept)) {
			return false;
		}
		Dept other = (Dept) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (deptName == null) {
			if (other.deptName != null) {
				return false;
			}
		} else if (!location.equals(other.location)) {
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
		return "Dept [No=" + id + ", name=" + deptName + ", location="
				+ location + "CustomField <"+getCustomField() + ">]\n";
	}
}
