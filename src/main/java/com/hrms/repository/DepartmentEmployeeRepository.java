package com.hrms.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.entities.DepartmentEmployee;
import com.hrms.entities.DepartmentEmployeeId;
import com.hrms.entities.Employee;

@Repository
public interface DepartmentEmployeeRepository extends JpaRepository<DepartmentEmployee, DepartmentEmployeeId> {

	
	DepartmentEmployee findByEmployee_EmpNoAndDepartment_DeptNoAndFromDate(int empNo, String deptNo, Date fromDate);

	
	DepartmentEmployee findByDepartment_DeptNoAndFromDate(String deptNo, Date fromDate);
	

	DepartmentEmployee findByEmployee_EmpNoAndFromDate(int empNo, Date fromDate);
	

	DepartmentEmployee findByEmployee_EmpNoAndDepartment_DeptNo(int empNo, String deptNo);
	
	
	List<DepartmentEmployee> findByEmployee_EmpNo(int empNo);
	
	
	@Query("SELECT dm FROM DepartmentEmployee dm WHERE dm.employee.empNo = :empNo")
	DepartmentEmployee findByEmployeeEmpNo(int empNo);


	
	List<DepartmentEmployee> findByDepartment_DeptNo(String deptNo);

	
	



	List<DepartmentEmployee> findByDepartmentDeptNoAndFromDate(String deptNo, Date fromDate);
	
	@Query("SELECT e FROM DepartmentEmployee e WHERE e.department.deptNo = :deptNo AND YEAR(e.fromDate) = :year")
	List<DepartmentEmployee> findEmployeesByDepartmentAndYear(@Param("deptNo") String deptNo, @Param("year") int year);



	@Modifying
	@Query("DELETE FROM DepartmentEmployee dm WHERE dm.employee.empNo = :empNo AND dm.department.deptNo = :deptNo AND dm.fromDate = :fromDate")
	void deleteByEmpNoAndDeptNoAndFromDate(@Param("empNo") int empNo, @Param("deptNo") String deptNo,
			@Param("fromDate") Date fromDate);
	
	@Query("SELECT dm FROM DepartmentEmployee dm WHERE dm.employee.email = :email AND dm.employee.password = :password AND dm.department.deptNo = :deptNo")
	Optional<DepartmentEmployee> findTheHr(@Param("email") String email, @Param("password") String password, @Param("deptNo") String deptNo);
	


	@Modifying
	@Query("DELETE FROM DepartmentEmployee dm WHERE dm.employee.empNo = :empNo AND dm.department.deptNo = :deptNo")
	void deleteByEmpNoAndDeptNo(@Param("empNo") int empNo, @Param("deptNo") String deptNo);

	@Modifying
	@Query("DELETE FROM DepartmentEmployee dm WHERE dm.employee.empNo = :empNo")
	void deleteByEmpNo(@Param("empNo") int empNo);

	
	@Modifying
	@Query("DELETE FROM DepartmentEmployee dm WHERE dm.employee.empNo = :empNo AND dm.fromDate = :fromDate")
	void deleteByEmpNoAndFromDate(@Param("empNo") int empNo, @Param("fromDate") Date fromDate);

	@Modifying
	@Query("DELETE FROM DepartmentEmployee dm WHERE dm.department.deptNo = :deptNo AND dm.fromDate = :fromDate")
	void deleteByDeptNoAndFromDate(@Param("deptNo") String deptNo, @Param("fromDate") Date fromDate);
	
	@Modifying
	@Query("DELETE FROM DepartmentEmployee dm WHERE dm.department.deptNo = :deptNo")
	void deleteByDeptNo(@Param("deptNo") String deptNo);
	
    List<DepartmentEmployee> findByEmployee(Employee employee);

    @Modifying
	@Query("DELETE FROM DepartmentEmployee dm WHERE dm.employee.empNo = :empNo")
	void deletebyEmpNo(int empNo);

	
	
	
	

}
