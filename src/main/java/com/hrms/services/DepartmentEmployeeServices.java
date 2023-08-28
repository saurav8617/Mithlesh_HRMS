package com.hrms.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.entities.DepartmentEmployee;
import com.hrms.repository.DepartmentEmployeeRepository;

@Service
public class DepartmentEmployeeServices {
	
	@Autowired
	private  DepartmentEmployeeRepository departmentEmployeeRepository;
	
	public List<DepartmentEmployee> getdepartmentemployee() {
		return 	departmentEmployeeRepository.findAll();
	}
	
	 
	 public List<DepartmentEmployee> findByDeptNoAndFromDate(String deptNo, Date fromDate) {
	        return departmentEmployeeRepository.findByDepartmentDeptNoAndFromDate(deptNo, fromDate);
	    }
	 
	 public List<DepartmentEmployee> findByDeptNo(String deptNo) {
	        return departmentEmployeeRepository.findByDepartment_DeptNo(deptNo);
	    }
	 
	 public DepartmentEmployee findBysDeptNoAndFromDate(String deptNo, Date fromDate) {
	        return departmentEmployeeRepository.findByDepartment_DeptNoAndFromDate(deptNo, fromDate);
	    }
	    
	    public DepartmentEmployee findByempNoAndFromDate(int empNo, Date fromDate) {
	        return departmentEmployeeRepository.findByEmployee_EmpNoAndFromDate(empNo, fromDate);
	    }
	    
	    public DepartmentEmployee getDepartEmployeeByEmpNoAndDeptNo(int empNo, String deptNo) {
	        return departmentEmployeeRepository.findByEmployee_EmpNoAndDepartment_DeptNo(empNo, deptNo);
	 }
	    public List<DepartmentEmployee> getDepartEmployeeByEmpNo(int empNo) {
	        return departmentEmployeeRepository.findByEmployee_EmpNo(empNo);
	 }
	    
	    public DepartmentEmployee getDepartEmployeeEmpNo(int empNo) {
	        return departmentEmployeeRepository.findByEmployeeEmpNo(empNo);
	 }
	    
	    
	    public DepartmentEmployee getDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(int empNo, String deptNo, Date fromDate) {
	        return departmentEmployeeRepository.findByEmployee_EmpNoAndDepartment_DeptNoAndFromDate(empNo, deptNo, fromDate);
	    }
	    public DepartmentEmployee updateDepartmentEmployee(DepartmentEmployee departmentEmployee) {
	        return departmentEmployeeRepository.save(departmentEmployee);
	    }
	    
	    public DepartmentEmployee saveDepartmentEmployee(DepartmentEmployee departmentEmployee) {
	        return departmentEmployeeRepository.save(departmentEmployee);
	    }
	    
	    public List<DepartmentEmployee> getEmployeesByDepartmentAndYear(String deptNo, int year) {
	        return departmentEmployeeRepository.findEmployeesByDepartmentAndYear(deptNo, year);
	    }
	    	
	    
	    public DepartmentEmployee findByEmailAndPassword(DepartmentEmployee request) {
	    	return  departmentEmployeeRepository.findTheHr(request.getEmployee().getEmail(), request.getEmployee().getPassword(), "d003").orElse(null);
	    }
	     
	   	    
	    @Transactional
	    public void deleteByEmpNoAndDeptNoAndFromDate(int empNo, Date fromDate, String deptNo) {
	    	departmentEmployeeRepository.deleteByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDate);
	    }

	    @Transactional
	    public void deleteByEmpNoAndDeptNo(int empNo, String deptNo) {
	    	departmentEmployeeRepository.deleteByEmpNoAndDeptNo(empNo, deptNo);
	    }

	    @Transactional
	    public void deleteByEmpNoAndFromDate(int empNo, Date fromDate) {
	    	departmentEmployeeRepository.deleteByEmpNoAndFromDate(empNo, fromDate);
	    }

	    @Transactional
	    public void deleteByDeptNoAndFromDate(String deptNo, Date fromDate) {
	    	departmentEmployeeRepository.deleteByDeptNoAndFromDate(deptNo, fromDate);
	    }

	    @Transactional

		public void deleteBysDeptNo(String deptNo) {
	    	departmentEmployeeRepository.deleteByDeptNo(deptNo);

		}
	    
	    @Transactional
	    public void deleteByEmpNo(int empNo) {
	    	departmentEmployeeRepository.deletebyEmpNo(empNo);
	    }


	    
	    
	    
	 
	 
	 
}
