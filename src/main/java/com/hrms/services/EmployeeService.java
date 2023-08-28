package com.hrms.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.entities.Employee;
import com.hrms.entities.Gender;
import com.hrms.exceptions.NotFoundException;
import com.hrms.repository.DepartmentEmployeeRepository;
import com.hrms.repository.DepartmentManagerRepository;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.SalariesRepository;
import com.hrms.repository.TitlesRepository;




 

@Service
public class EmployeeService {

 

    @Autowired
    private EmployeeRepository employeeRepository;
    
	@Autowired
	private  DepartmentEmployeeRepository departmentEmployeeRepository;
	

    @Autowired
    private SalariesRepository salaryRepository;

	@Autowired
    private TitlesRepository titlesRepository;
	
	@Autowired
	private DepartmentManagerRepository departmentManagerRespository; 

    public List<Employee> getEmployee() {
        return employeeRepository.findAll();
    }

 

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }
    
//    public List<Employee> getEmployeesJoinedLast10Years() {
//        LocalDate tenYearsAgo = LocalDate.now().minusYears(10);
//        return employeeRepository.findEmployeesJoinedLast10Years(tenYearsAgo);
//    }
//
//    public int getCountEmployeesJoinedLast10Years() {
//        LocalDate tenYearsAgo = LocalDate.now().minusYears(10);
//        return employeeRepository.countEmployeesJoinedLast10Years(tenYearsAgo);
//    }
    public List<Employee> getEmployeesJoinedLast10Years() {
        LocalDate tenYearsAgo = LocalDate.now().minusYears(10);
        Date tenYearsAgoDate = Date.valueOf(tenYearsAgo);
        return employeeRepository.findEmployeesJoinedLast10Years(tenYearsAgoDate);
    }

    public int getCountEmployeesJoinedLast10Years() {
        LocalDate tenYearsAgo = LocalDate.now().minusYears(10);
        Date tenYearsAgoDate = Date.valueOf(tenYearsAgo);
        return employeeRepository.countEmployeesJoinedLast10Years(tenYearsAgoDate);
    }
    
    public List<Employee> getEmployeesJoinedAfter2005(int year) {
        return employeeRepository.findEmployeesJoinedAfterYear(year);
    }
    
    public int getCountEmployeesJoinedAfter2005(int year) {
        return employeeRepository.countEmployeesJoinedAfterYear(year);
    }
       

    public List<Employee> getEmployeeByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }

 

    public List<Employee> getEmployeeByLastName(String lastName) {
        return employeeRepository.findByLastName(lastName);
    }
    
    public List<Employee> getEmployeeByemail(String email) {
        return employeeRepository.findByEmailContains(email);
    }

 

    public List<Employee> getEmployeesByGender(Gender gender) {
        try {
            List<Employee> records = employeeRepository.findByGenderContains(gender);
            System.out.println(records);
            return records;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public int getGenderCount(Gender gender) {
        return employeeRepository.findByGenderCount(gender);
    }
    
    public List<Employee> getAwardedEmployeesWithExperience(int yearsOfExperience) {
        return employeeRepository.findEmployeesWithExperience(yearsOfExperience);
    }
    
    
    public List<Employee> getEmployeesByHireDate(Date hireDate) {
        return employeeRepository.findByHireDate(hireDate);
    }

 

    public List<Employee> getEmployeesByBirthDate(Date birthDate) {
        return employeeRepository.findByBirthDate(birthDate);
    }

 

    public List<Employee> getAllEmployeesSortedByHireDateDesc() {
        return employeeRepository.findAllByOrderByHireDateDesc();
    }

 

    public Employee addEmployee(Employee employee) {
        Employee newEmployee = employeeRepository.save(employee);
        return newEmployee;
    }

 
//
//    public Employee updateEmployee(Employee employee) {
//        Employee readEmployee = employeeRepository.findById(employee.getEmpNo()).orElse(null);
//        readEmployee.setFirstName(employee.getFirstName());
//        readEmployee.setLastName(employee.getLastName());
//        readEmployee.setBirthDate(employee.getBirthDate());
//        readEmployee.setHireDate(employee.getHireDate());
//        readEmployee.setGender(employee.getGender());
//        readEmployee.setEmail(employee.getEmail());
//        readEmployee.setPassword(employee.getPassword());
//        return employeeRepository.save(readEmployee);
//    }
    public Employee updateEmployeefirstNameLastName(Employee employee) {
        Employee readEmployee = employeeRepository.findById(employee.getEmpNo()).orElse(null);
        if (readEmployee != null) {
            readEmployee.setFirstName(employee.getFirstName());
            readEmployee.setLastName(employee.getLastName());          
            return employeeRepository.save(readEmployee);
        } else {
            throw new NotFoundException("Employee not found");
        }
    }


 

    public Employee updateEmployee(Employee employee, int empNo) {
        Employee readEmployee = employeeRepository.findById(empNo).orElse(null);
        if (readEmployee != null) {
            readEmployee.setFirstName(employee.getFirstName());
            readEmployee.setLastName(employee.getLastName());
            readEmployee.setBirthDate(employee.getBirthDate());
            readEmployee.setHireDate(employee.getHireDate());
            readEmployee.setGender(employee.getGender());
            readEmployee.setEmail(employee.getEmail());
            readEmployee.setPassword(employee.getPassword());
            return employeeRepository.save(readEmployee);
        }
        return null;
    }

 

    public Employee updateEmployeeFirstName(Employee employee, int empNo) {
        Employee readEmployee = employeeRepository.findById(empNo).orElse(null);
        if (readEmployee != null) {
            readEmployee.setFirstName(employee.getFirstName());
            return employeeRepository.save(readEmployee);
        }
        return null;
    }

 

    public Employee updateEmployeeByHireDate(Employee employee, int empNo) {
        Employee readEmployee = employeeRepository.findById(empNo).orElse(null);
        readEmployee.setHireDate(employee.getHireDate());
        return employeeRepository.save(readEmployee);
    }

 

    public Employee updateEmployeeBirthDate(Employee employee, int empNo) {
        Employee readEmployee = employeeRepository.findById(empNo).orElse(null);
        readEmployee.setBirthDate(employee.getBirthDate());
        return employeeRepository.save(readEmployee);
    }
    
    public Employee upadateEmailAndPassword(Employee employee) {
    	return employeeRepository.save(employee);
    }
    
    public Employee findByEmailAndPassword(String email, String password) {
        return employeeRepository.findByEmailAndPassword(email, password);
    }
 

    public Optional<Employee> findByempNo(int empNo) {
    	return employeeRepository.findById(empNo);
    }
 
    public Employee updateemployee(Employee employee) {
    	return employeeRepository.save(employee);
    }
    
    public int getIdByEmail(String email) {
    	Employee emp=employeeRepository.findByEmail(email);
    	int id=emp.getEmpNo();
    	return id;
    }
    
    public List<Employee> getEmployeesByHireDateRange(int startYear, int endYear) {
        return employeeRepository.findEmployeesByHireDateRange(startYear, endYear);
    }
    
    @Transactional
    public void deleteByEmpNo(int empNo) {
    	employeeRepository.deleteByempNo(empNo);
    }
    

    
    @Transactional
    public void deleteEmployeeByEmpNo(int empNo) {
        Employee employee = employeeRepository.findById(empNo).orElse(null);
        if (employee != null) {
        	
        	departmentEmployeeRepository.deleteByEmpNo(empNo);
        	salaryRepository.deleteByEmployeeEmpNo(empNo);
        	titlesRepository.deleteByEmployee_EmpNo(empNo);
        	departmentManagerRespository.deleteByEmpNo(empNo);
            employeeRepository.deleteByempNo(empNo);
            
        }}
    
    public Employee forgotPassword(int empNo, String email, Employee employee) {
        Employee readEmployee = employeeRepository.findByEmpNoAndEmail(empNo, email);
        if (readEmployee != null) {
            readEmployee.setPassword(employee.getPassword());
            System.out.println("password in Service "+ employee.getPassword());
            System.out.println("email in Service "+ readEmployee);
            System.out.println("employee object"+ employee);
            return employeeRepository.save(readEmployee);
        } else {
            throw new NotFoundException("Employee not found");
        }
    }
    
    



}