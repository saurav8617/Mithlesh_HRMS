package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.entities.Departments;
import com.hrms.exceptions.InvalidDataException;
import com.hrms.services.DepartmentSerives;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/api/v1/Departments")
@CrossOrigin
public class DepartmentsController {
	
	
	@Autowired
    private DepartmentSerives departmentServices;

 

    @GetMapping("/all")
//    @Operation(summary = "Fetch all departments")
    public List<Departments> findAlldepartments() {
        return departmentServices.getDepartments();
    }

 

    @GetMapping("/{deptNo}")
    @Operation(summary = "Search department by deptno")
    public List<Departments> findAllDepartmentsBydeptNo(@PathVariable("deptNo") String deptNo) {
        return departmentServices.getDepartmentsByDepNo(deptNo);
    }

 

    @GetMapping("/name/{deptName}")
    @Operation(summary = "Search department by name")
    public List<Departments> findAllDepartmentsByDeptName(@PathVariable("deptName") String deptName) {
        return departmentServices.getDepartmentsByDeptName(deptName);
    }

 

    @PostMapping("/add")
    @Operation(summary = "Add new department")
    public ResponseEntity<String> addDepartment(@RequestBody Departments departmentManager) {
        Departments department = departmentServices.addDepartment(departmentManager);

        if (department != null) {
            return new ResponseEntity<String>("New department added successfully", HttpStatus.OK);
        } else
            throw new InvalidDataException("Validation Failed");
    }

 

    @PutMapping("/deptno/{deptNo}")
    @Operation(summary = "Update department by department deptno")
    public ResponseEntity<String> updateDepartmentByDeptNo(@PathVariable String deptNo,
            @RequestBody Departments departments) {
        Departments updatedManager = departmentServices.updateByDeptNo(departments);
        if (updatedManager != null) {
            return new ResponseEntity<String>("Department updated successfully", HttpStatus.OK);
        } else
            throw new InvalidDataException("Updation Failed");

 

    }

    @PutMapping("/deptname/{deptName}")
    @Operation(summary = "Update department by name")
    public ResponseEntity<String> updateDepartmentByDeptName(@PathVariable String deptName,
            @RequestBody Departments departments) {
        Departments updatedManager = departmentServices.updateByDeptName(departments);
        if (updatedManager != null)
            return new ResponseEntity<String>("Department updated successfully", HttpStatus.OK);
        else
            throw new InvalidDataException("Updation Failed");
    }
	
	 @DeleteMapping("deptNo/{deptNo}")
	    public ResponseEntity<Void> deleteDepartmentByDeptNo(@PathVariable String deptNo) {
		 departmentServices.deleteByDeptNo(deptNo);
	        return ResponseEntity.noContent().build();
	    }

	 
	 @DeleteMapping("/deptmentName/{deptName}")
	    public ResponseEntity<String> deleteDepartmentByDeptName(@RequestParam("deptName") String deptName) {
		 departmentServices.deleteByDeptName(deptName);
	        return ResponseEntity.ok("Department deleted successfully");
	    }
	 
	 
	 
		
	


}
