package com.hrms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.entities.Departments;
import com.hrms.repository.DepartmentEmployeeRepository;
import com.hrms.repository.DepartmentManagerRepository;
import com.hrms.repository.DepartmentsRepository;
import com.hrms.repository.EmployeeRepository;

@Service
public class DepartmentSerives {

 

    @Autowired
    private DepartmentsRepository departmentRepository;

	@Autowired
	private DepartmentManagerRepository departmentManagerRespository; 
	
	@Autowired
	private  DepartmentEmployeeRepository departmentEmployeeRepository;
	
    @Autowired
    private EmployeeRepository employeeRepository;
    
    

    public List<Departments> getDepartments() {
        return departmentRepository.findAll();
    }

 

    public List<Departments> getDepartmentsByDepNo(String deptNo) {
        if (deptNo != null)
            return departmentRepository.findByDeptNo(deptNo);
        else
            return null;
    }

 

    public List<Departments> getDepartmentsByDeptName(String deptName) {
        if (deptName != null)
            return departmentRepository.findByDeptName(deptName);
        else
            return null;
    }

 

    public Departments addDepartment(Departments departments) {
        return departmentRepository.save(departments);
    }

 

    public Departments updateByDeptNo(Departments departments) {
        return departmentRepository.save(departments);
    }

 

    public Departments updateByDeptName(Departments departments) {
        return departmentRepository.save(departments);
    }
    
    @Transactional
    public void deleteByDeptNo(String deptNo) {
    	Departments department=departmentRepository.findByDepartmentNumber(deptNo);
    	if(department!=null) {
    		departmentManagerRespository.deleteByDeptNo(deptNo);
    		departmentEmployeeRepository.deleteByDeptNo(deptNo);
        	departmentRepository.deleteByDeptNo(deptNo);
    	}
    }
    
    @Transactional
    public void deleteByDeptName(String deptName) {
    	departmentRepository.deleteByDeptName(deptName);
    }
    
    
}
