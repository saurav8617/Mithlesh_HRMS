package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.entities.Departments;
@Repository
public interface DepartmentsRepository extends JpaRepository<Departments, Integer>{
	

	@Query("SELECT d FROM Departments d WHERE d.deptNo = :deptNo")
    List<Departments> findByDeptNo(@Param("deptNo") String deptNo);


    @Query("SELECT d FROM Departments d WHERE d.deptName = :deptName")
    List<Departments> findByDeptName(@Param("deptName") String deptName);
	
    @Modifying
    @Query("DELETE  FROM Departments d WHERE d.deptNo = :deptNo")
	void deleteByDeptNo(@Param("deptNo")String deptNo);
	
    @Modifying
    @Query("DELETE  FROM Departments d WHERE d.deptName = :deptName")
    void deleteByDeptName(@Param("deptName")String deptName);


    @Query("SELECT d FROM Departments d WHERE d.deptNo = :deptNo")
    Departments findByDepartmentNumber(@Param("deptNo") String deptNo);
    
    


}
