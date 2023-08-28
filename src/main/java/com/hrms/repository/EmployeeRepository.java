package com.hrms.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrms.entities.Employee;
import com.hrms.entities.Gender;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	 

	    List<Employee> findByFirstName(String firstName);
	    
	    Employee findByEmailAndPassword(String email, String password);

	    List<Employee> findByLastName(String lastName);

	    Employee findByEmpNo(int empNo);
	    
	    Employee findByEmpNoAndEmail(int empNo, String email);

	    @Query("SELECT E FROM Employee E WHERE E.gender=?1")
	    List<Employee> findByGenderContains(Gender gender);


	    @Query("SELECT COUNT(e) FROM Employee e WHERE e.gender = ?1")
	    int findByGenderCount(Gender gender);
	    
	    
	    
	    @Query("SELECT e FROM Employee e WHERE YEAR(CURRENT_DATE) - YEAR(e.hireDate) >= :yearsOfExperience")
	    List<Employee> findEmployeesWithExperience(@Param("yearsOfExperience") int yearsOfExperience);


	    List<Employee> findByHireDate(Date hireDate);

	    List<Employee> findByBirthDate(Date birthDate);

	 

	    @Query("SELECT E FROM Employee E ORDER BY E.hireDate DESC")
	    List<Employee> findAllByOrderByHireDateDesc();
	    
	    
	    @Query("SELECT e FROM Employee e WHERE e.hireDate >= :date")
	    List<Employee> findEmployeesJoinedLast10Years(@Param("date") Date date);
	    
	    @Query("SELECT COUNT(e) FROM Employee e WHERE e.hireDate >= :date")
	    int countEmployeesJoinedLast10Years(@Param("date") Date date);
	    
	    @Query("SELECT e FROM Employee e WHERE YEAR(e.hireDate) > :year")
	    List<Employee> findEmployeesJoinedAfterYear(@Param("year") int year);
	    
	    @Query("SELECT COUNT(e) FROM Employee e WHERE YEAR(e.hireDate) > :year")
	    int countEmployeesJoinedAfterYear(@Param("year") int year);

	    @Modifying
	    @Query("DELETE FROM Employee e WHERE e.empNo = :empNo")
		void deleteByempNo(@Param("empNo") int empNo);




		List<Employee> findByEmailContains(String email);
		
		Employee findByEmail(String email);
		
		 @Query("SELECT e FROM Employee e WHERE YEAR(e.hireDate) BETWEEN :startYear AND :endYear")
		 List<Employee> findEmployeesByHireDateRange(@Param("startYear") int startYear, @Param("endYear") int endYear);




}





