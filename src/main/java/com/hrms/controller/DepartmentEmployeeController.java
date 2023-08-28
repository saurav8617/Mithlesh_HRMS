package com.hrms.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hrms.entities.DepartmentEmployee;
import com.hrms.exceptions.InvalidDataException;
import com.hrms.services.DepartmentEmployeeServices;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/deptemp")
@CrossOrigin
public class DepartmentEmployeeController {
	
	@Autowired
	private DepartmentEmployeeServices departmentEmployeeServices;
	
	
	@GetMapping("/all")
	public List<DepartmentEmployee> findAlldepartmentsemployee() {
		return departmentEmployeeServices.getdepartmentemployee();
	}
	
	 @GetMapping("/empno/{empNo}/deptno/{deptNo}")
	    public DepartmentEmployee findByEmpNoAndDeptNo(
	            @PathVariable("empNo") int empNo,
	            @PathVariable("deptNo") String deptNo
	    ) {
	        return departmentEmployeeServices.getDepartEmployeeByEmpNoAndDeptNo(empNo, deptNo);
	    }
	 
	 @GetMapping("/empno/{empNo}")
	    public List<DepartmentEmployee> findByEmpNo(
	            @PathVariable("empNo") int empNo
	    ) {
	        return departmentEmployeeServices.getDepartEmployeeByEmpNo(empNo);
	    }
	 
	 @GetMapping("/department/{deptNo}/year/{year}")
	    public List<DepartmentEmployee> getEmployeesByDepartmentAndYear(
	            @PathVariable("deptNo") String deptNo,
	            @PathVariable("year") int year) {
	        return departmentEmployeeServices.getEmployeesByDepartmentAndYear(deptNo, year);
	    } 
	 @GetMapping("/deptno/{deptNo}/fromdate/{fromDate}")
	    public List<DepartmentEmployee> findByDeptNoFromDate(
	            @PathVariable("deptNo") String deptNo,
	            @PathVariable("fromDate")  Date fromDate
	    ) {
	        List<DepartmentEmployee> departmentManagers = departmentEmployeeServices.findByDeptNoAndFromDate(deptNo, fromDate);
	        System.out.println(departmentManagers);
	        return departmentManagers;
	    }
	 
	 @GetMapping("/deptno/{deptNo}")
	    public List<DepartmentEmployee> findByDeptNo(
	            @PathVariable("deptNo") String deptNo) {
	        List<DepartmentEmployee> departmentEmployee = departmentEmployeeServices.findByDeptNo(deptNo);
	        System.out.println(departmentEmployee);
	        return departmentEmployee;
	    }
		
		@GetMapping("/empno/{empNo}/fromdate/{fromDate}")
	    public DepartmentEmployee findByempNoFromDate(
	            @PathVariable("empNo") int empNo,
	            @PathVariable("fromDate") Date fromDate
	    ) {
	        DepartmentEmployee departmentManagers = departmentEmployeeServices.findByempNoAndFromDate(empNo, fromDate);
	        System.out.println(departmentManagers);
	        return departmentManagers;
	    }
		
		@GetMapping("/empno/{empNo}/deptno/{deptNo}/fromdate/{fromDate}")
		public DepartmentEmployee findbyempnodeptnoAndfromdate(@PathVariable("empNo") int empNo, @PathVariable("deptNo") String deptNo, @PathVariable("fromDate") Date fromDate) {
		    return departmentEmployeeServices.getDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDate);
		}

		
	    @PostMapping("/add")
	    @Operation(summary = "Add new saveDepartmentEmployee object in DB")
	    public ResponseEntity<DepartmentEmployee> addNewdepartmentemployee(@RequestBody DepartmentEmployee departmentEmployee) {
	    	DepartmentEmployee createdTitle = departmentEmployeeServices.saveDepartmentEmployee(departmentEmployee);
	        if (createdTitle != null) {
	            return ResponseEntity.status(HttpStatus.OK).body(createdTitle);
	        } else {
	            throw new InvalidDataException("Validation Failed");
	        }
	    }
		
		@Transactional
		@PutMapping("/empNo/{empNo}/deptNo/{deptNo}/fromDate/{fromDate}")
		public ResponseEntity<DepartmentEmployee> updateDepartmentEmployee(
		    @PathVariable int empNo,
		    @PathVariable String deptNo,
		    @PathVariable("fromDate") Date fromDate,
		    @RequestBody DepartmentEmployee departmentEmployee) {
		    
		    DepartmentEmployee existingEmployee = departmentEmployeeServices.getDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDate);
		    if (existingEmployee != null) {
		        existingEmployee.getEmployee().setFirstName(departmentEmployee.getEmployee().getFirstName());
		        existingEmployee.getEmployee().setLastName(departmentEmployee.getEmployee().getLastName());
		        existingEmployee.getEmployee().setBirthDate(departmentEmployee.getEmployee().getBirthDate());
		        existingEmployee.getEmployee().setGender(departmentEmployee.getEmployee().getGender());
		        existingEmployee.setFromDate(departmentEmployee.getFromDate());
		        existingEmployee.setToDate(departmentEmployee.getToDate());
		        
		        DepartmentEmployee updatedEmployee = departmentEmployeeServices.updateDepartmentEmployee(existingEmployee);

		        return ResponseEntity.ok(updatedEmployee);
		    } else {
		        return ResponseEntity.notFound().build();
		    }
		}
		
		@Transactional
		@PutMapping("/empNo/{empNo}/fromDate/{fromDate}")
		public ResponseEntity<DepartmentEmployee> updateDepartmentEmployee(
		    @PathVariable int empNo,
		    @PathVariable("fromDate")Date fromDate,
		    @RequestBody DepartmentEmployee departmentEmployee) {
		    
		    DepartmentEmployee existingEmployee = departmentEmployeeServices.findByempNoAndFromDate(empNo, fromDate);
		    if (existingEmployee != null) {
		        existingEmployee.getEmployee().setFirstName(departmentEmployee.getEmployee().getFirstName());
		        existingEmployee.getEmployee().setLastName(departmentEmployee.getEmployee().getLastName());
		        existingEmployee.getEmployee().setBirthDate(departmentEmployee.getEmployee().getBirthDate());

		        DepartmentEmployee updatedEmployee = departmentEmployeeServices.updateDepartmentEmployee(existingEmployee);

		        return ResponseEntity.ok(updatedEmployee);
		    } else {
		        return ResponseEntity.notFound().build();
		    }
		}
		@Transactional
		@PutMapping("/empNo/{empNo}/deptNo/{deptNo}")
		public ResponseEntity<DepartmentEmployee> updateDepartmentEmployee(
		    @PathVariable int empNo,
		    @PathVariable String deptNo,
		    @RequestBody DepartmentEmployee departmentEmployee) {
		    
		    DepartmentEmployee existingEmployee = departmentEmployeeServices.getDepartEmployeeByEmpNoAndDeptNo(empNo, deptNo);
		    if (existingEmployee != null) {
		        existingEmployee.getEmployee().setFirstName(departmentEmployee.getEmployee().getFirstName());
		        existingEmployee.getEmployee().setLastName(departmentEmployee.getEmployee().getLastName());
		        existingEmployee.getEmployee().setBirthDate(departmentEmployee.getEmployee().getBirthDate());

		        
		        DepartmentEmployee updatedEmployee = departmentEmployeeServices.updateDepartmentEmployee(existingEmployee);

		        return ResponseEntity.ok(updatedEmployee);
		    } else {
		        return ResponseEntity.notFound().build();
		    }
		}
		@Transactional
		@PutMapping("/deptNo/{deptNo}/fromDate/{fromDate}")
		public ResponseEntity<DepartmentEmployee> updateDepartmentEmployee(
		    @PathVariable String deptNo,
		    @PathVariable("fromDate") Date fromDate,
		    @RequestBody DepartmentEmployee departmentEmployee) {
		    
		    DepartmentEmployee existingEmployee = departmentEmployeeServices.findBysDeptNoAndFromDate(deptNo, fromDate);
		    if (existingEmployee != null) {
		        existingEmployee.getEmployee().setFirstName(departmentEmployee.getEmployee().getFirstName());
		        existingEmployee.getEmployee().setLastName(departmentEmployee.getEmployee().getLastName());
		        existingEmployee.getEmployee().setBirthDate(departmentEmployee.getEmployee().getBirthDate());
		        
		        DepartmentEmployee updatedEmployee = departmentEmployeeServices.updateDepartmentEmployee(existingEmployee);

		        return ResponseEntity.ok(updatedEmployee);
		    } else {
		        return ResponseEntity.notFound().build();
		    }
		}
		@DeleteMapping("/empno/{empNo}/deptno/{deptNo}/fromdate/{fromDate}")
	    public ResponseEntity<Void> deleteDepartmentEmployeeByEmpNoAndDeptNoAndFromDate(
	            @PathVariable int empNo,
	            @PathVariable String deptNo,
	            @PathVariable Date fromDate) {
			departmentEmployeeServices.deleteByEmpNoAndDeptNoAndFromDate(empNo, fromDate, deptNo);
	        return ResponseEntity.noContent().build();
	    }

	    @DeleteMapping("/empno/{empNo}/deptno/{deptNo}")
	    public ResponseEntity<Void> deleteDepartmentEmployeeByEmpNoAndDeptNo(
	            @PathVariable int empNo,
	            @PathVariable String deptNo) {
	    	departmentEmployeeServices.deleteByEmpNoAndDeptNo(empNo, deptNo);
	        return ResponseEntity.noContent().build();
	    }

	    @DeleteMapping("/empno/{empNo}/fromdate/{fromDate}")
	    public ResponseEntity<Void> deleteDepartmentEmployeeByEmpNoAndFromDate(
	            @PathVariable int empNo,
	            @PathVariable Date fromDate) {
	    	departmentEmployeeServices.deleteByEmpNoAndFromDate(empNo, fromDate);
	        return ResponseEntity.noContent().build();
	    }

	    @DeleteMapping("/deptno/{deptNo}/fromdate/{fromDate}")
	    public ResponseEntity<Void> deleteDepartmentEmployeeByDeptNoAndFromDate(
	            @PathVariable String deptNo,
	            @PathVariable Date fromDate) {
	    	departmentEmployeeServices.deleteByDeptNoAndFromDate(deptNo, fromDate);
	        return ResponseEntity.noContent().build();
	    }
	    
	    @DeleteMapping("/deptno/{deptNo}")
	    public ResponseEntity<Void> deleteDepartmentEmployeeByDeptNo(
	            @PathVariable String deptNo) {
	    	departmentEmployeeServices.deleteBysDeptNo(deptNo);
	        return ResponseEntity.noContent().build();
	    }
	    
	    
	    @PostMapping("/adminlogin")
		public ResponseEntity<DepartmentEmployee> loginEmployee(@RequestBody DepartmentEmployee request) {
	    	DepartmentEmployee employee = departmentEmployeeServices.findByEmailAndPassword(request);
		    if (employee != null) {
		        return ResponseEntity.ok(employee);
		    } else {
		        return ResponseEntity.badRequest().build();
		    }
		}
	    


	    
		

}
