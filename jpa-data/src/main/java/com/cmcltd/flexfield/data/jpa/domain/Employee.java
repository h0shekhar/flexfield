package com.cmcltd.flexfield.data.jpa.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

@Entity(name = "EMP")
@Table(name = "EMP")
public class Employee extends BaseBusinessEntity {
	
	private static final Logger logger = LoggerFactory.getLogger(Employee.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "EMPNO_GEN", sequenceName = "EMP_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPNO_GEN")
	@Column(name = "EMPNO", nullable = false, length = 4)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer empNo) {
		this.id = empNo;
	}

	@Column(name = "ENAME", nullable = false, length = 10)
	private String ename;

	public String getEname() {
		return ename;
	}

	public void setEname(String name) {
		this.ename = name;
	}

	@Column(name = "JOB", nullable = false, length = 9)
	private String job;

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Column(name = "MGR", nullable = true, length = 4)
	private Integer mgr;

	public Integer getMgr() {
		return mgr;
	}

	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}

	@Column(name = "HIREDATE", nullable = false)
	private Date hireDate;

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hiredOn) {
		hireDate = hiredOn;
	}

	@Column(name = "SAL", precision = 7, scale = 2)
	private BigDecimal salary;

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal sal) {
		this.salary = sal;
	}

	@Column(name = "COMM", precision = 7, scale = 2)
	private BigDecimal commission;

	public BigDecimal getCommision() {
		return commission;
	}

	public void setCom(BigDecimal com) {
		this.commission = com;
	}

	@ManyToOne
	@JoinColumn(name = "DEPTNO", referencedColumnName = "DEPTNO")
	private Dept dept;

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept department) {
		this.dept = department;
	}
	
	

	public Employee(int employeeNo, String empName, String job, int mgr,
			BigDecimal sal, BigDecimal com, Dept dept) {
		Assert.hasText(empName, "Employee Name cannot be NULL.");
		Assert.hasText(job, "Job cannot be NULL.");
		Assert.isNull(sal, "Salary cannot be NULL");
		Assert.isNull(employeeNo, "Employee No cannot be NULL");
		Assert.isNull(dept, "Department No cannot be NULL");

		this.id = employeeNo;
		this.ename = empName;
		this.job = job;
		this.mgr = mgr;
		this.salary = sal;
		this.commission = com;
		this.dept = dept;
		setEntityName("EMP");
		logger.trace("Employee instance created");

	}

	public Employee() {
		setEntityName("EMP");
		logger.trace("Employee instance created");
	}

	public Employee(Integer empNo) {
		logger.trace("Employee instance created");
		this.id = empNo;
		setEntityName("EMP");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ename == null) ? 0 : ename.hashCode());
		result = prime * result + ((job == null) ? 0 : job.hashCode());
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
		if (!(obj instanceof Employee)) {
			return false;
		}
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (ename == null) {
			if (other.ename != null) {
				return false;
			}
		} else if (!job.equals(other.job)) {
			return false;
		}
		if (mgr == null) {
			if (other.mgr != null) {
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
		return "Employee [No=" + id + ", name=" + ename + ", job=" + job
				+ " , Sal=" + salary + ", Manager =" + mgr + ", Dept="
				+ dept.getId()  + "CustomField <"+getCustomField() + ">]\n";

	}

}
