package com.cmcltd.flexfield.data.jpa.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cmcltd.flexfield.data.jpa.domain.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	@Query("select e from EMP e where e.ename = upper(?1)")
	Employee findByEmployeeName(String name);

	@Query("select e from EMP e where e.id = ?1")
	Employee findById(Integer id);

	@Query("select e from EMP e where e.mgr = ?1")
	List<Employee> findByManager(Integer manager);

	
	@Query("select e from EMP e where e.dept.id = ?1")
	List<Employee> findByDeptno(Integer deptno);
}
