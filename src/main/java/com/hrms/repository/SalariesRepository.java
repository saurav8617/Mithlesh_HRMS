package com.hrms.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.entities.Employee;
import com.hrms.entities.Salaries;
import com.hrms.entities.SalaryId;


@Repository
public interface SalariesRepository extends JpaRepository<Salaries, SalaryId> {

	List<Salaries> findAllByFromDate(Date fromDate);

	@Query("select s from Salaries s where s.employee.empNo= :emp_no")
	List<Salaries> findSalaryByEmployee(@Param("emp_no") int empNo);

	@Query("SELECT s FROM Salaries s WHERE s.salary >= :minsalary AND s.salary <= :maxsalary")
	List<Salaries> findAllBySalaryBetween(@Param("minsalary") int minSalary, @Param("maxsalary") int maxSalary);

	@Query("select s from Salaries s where s.employee.empNo= :empNo And s.fromDate=:fromDate")
	Salaries findbyempnoandfromdate(@Param("empNo") int empNo, @Param("fromDate") Date fromDate);
	
	@Query("select s from Salaries s where s.employee.empNo= :emp_no")
	Salaries findsSalaryByEmployee(@Param("emp_no") int empNo);
	
	@Query("select s from Salaries s where s.fromDate=:fromDate")
	Salaries findbyfromdate(@Param("fromDate") Date fromDate);
	
    List<Salaries> findByEmployee(Employee employee);
	

	@Modifying
	@Query("DELETE FROM Salaries s WHERE s.employee.empNo = :empNo AND s.fromDate = :fromDate")
	void deleteByEmployeeEmpNoAndFromDate(@Param("empNo") int empNo, @Param("fromDate") Date fromDate);

	@Modifying
	@Query("DELETE FROM Salaries s WHERE s.fromDate = :fromDate")
	void deleteByFromDate(@Param("fromDate") Date fromDate);

	@Modifying
	@Query("DELETE FROM Salaries s WHERE s.employee.empNo = :empNo")
	void deleteByEmployeeEmpNo(@Param("empNo") int empNo);
	
	
}