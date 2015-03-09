package com.cmcltd.flexfield.data.jpa.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cmcltd.flexfield.data.jpa.domain.Dept;

public interface DeptRepository extends CrudRepository<Dept, Long> {

	@Query("select d from DEPT d where d.deptName = ?1")
	Dept findByDname(String name);

	@Query("select d from DEPT d where d.id = ?1")
	List<Dept> findByDeptNo(Integer deptNo);

	@Query("select d from DEPT d where d.location = ?1")
	List<Dept> findByLocation(String loc);

}
