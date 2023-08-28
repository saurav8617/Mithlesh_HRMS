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
import com.hrms.entities.DepartmentManager;
import com.hrms.entities.DepartmentManagerId;
import com.hrms.entities.Employee;
import com.hrms.exceptions.InvalidDataException;
import com.hrms.exceptions.NotFoundException;
import com.hrms.services.DepartmentEmployeeServices;
import com.hrms.services.DepartmentManagerService;
import com.hrms.services.DepartmentSerives;
import com.hrms.services.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/departmentmanager")
@CrossOrigin

public class DepartmentManagerController {

	@Autowired
	public DepartmentManagerService departmentManagerService;
	
	@Autowired
	public EmployeeService employeeService;
	
	
	@Autowired
    private DepartmentSerives departmentServices;
	
	@Autowired
	private DepartmentEmployeeServices departmentEmployeeServices;

 


	@GetMapping("/all")
	@Operation(summary = "Fetch all deptmanager objects")
	public List<DepartmentManager> findAllDepartmentManager() {
		return departmentManagerService.getDepartmentManager();
	}

	@GetMapping("/empno/{empNo}/deptno/{deptNo}")
	@Operation(summary = "Search deptmanager by empno and deptno")
	public DepartmentManager findByEmpNoDeptNo(@PathVariable("empNo") int empNo,
			@PathVariable("deptNo") String deptNo) {
		return departmentManagerService.getDepartmentManagersByEmpNoAndDeptNo(empNo, deptNo);
	}
	
	@GetMapping("deptno/{deptNo}")
	@Operation(summary = "Search deptmanager by deptno")
	public List<DepartmentManager> findByEmpNoDeptNo(
			@PathVariable("deptNo") String deptNo) {
		return departmentManagerService.findByDeptNo(deptNo);
	}

	@GetMapping("/deptno/{deptNo}/fromdate/{fromDate}")
	@Operation(summary = "Fetch all deptmanager objects by, deptno and from date")
	public List<DepartmentManager> findByDeptNoFromDate(@PathVariable("deptNo") String deptNo,
			@PathVariable("fromDate") Date fromDate) {
		List<DepartmentManager> departmentManagers = departmentManagerService.findByDeptNoAndFromDate(deptNo, fromDate);
		System.out.println(departmentManagers);
		return departmentManagers;
	}

	@GetMapping("/empno/{empNo}/fromdate/{fromDate}")
	@Operation(summary = "Fetch all deptmanager objects by, id and from date")
	public DepartmentManager findByEmpNoFromDate(@PathVariable("empNo") int empNo,
			@PathVariable("fromDate") Date fromDate) {
		DepartmentManager departmentManagers = departmentManagerService.findByEmpNoAndFromDate(empNo, fromDate);
		System.out.println(departmentManagers);
		return departmentManagers;
	}


	@GetMapping("/empno/{empNo}/deptno/{deptNo}/fromdate/{fromDate}")
	@Operation(summary = "Fetch all deptmanager objects by, id, deptno, and from date")
	public DepartmentManager findByEmpNoDeptNoAndFromDate(@PathVariable("empNo") int empNo,
			@PathVariable("deptNo") String deptNo,
			@PathVariable("fromDate")  Date fromDate) {
		return departmentManagerService.getDepartmentManagersByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDate);
	}

	 @GetMapping("/from-date/{fromDate}")
	    public List<Object[]> getEmployeesFromDate(@PathVariable("fromDate") Date fromDate) {
	        return departmentManagerService.getEmployeesFromDate(fromDate);
	    }

	@PostMapping("/add")
    @Operation(summary = "Add new saveDepartmentManager object in DB")
    public ResponseEntity<DepartmentManager> addNewdepartmentemployee(@RequestBody DepartmentManager departmentManager) {
		DepartmentManager createdTitle = departmentManagerService.saveDepartmentManager(departmentManager);
        if (createdTitle != null) {
            return ResponseEntity.status(HttpStatus.OK).body(createdTitle);
        } else {
            throw new InvalidDataException("Validation Failed");
        }
    }
	
	@Transactional
	@PutMapping("/deptNo/{deptNo}/fromdate/{fromDate}")
	public ResponseEntity<DepartmentManager> updateDepartmentManager(@PathVariable String deptNo,
			@PathVariable("fromDate") Date fromDate,
			@RequestBody DepartmentManager departmentManager) {
		DepartmentManager existingDepartment = departmentManagerService.getDepartmentDeptNoAndFromDate(deptNo,
				fromDate);
		if (existingDepartment != null) {
			existingDepartment.getEmployee().setFirstName(departmentManager.getEmployee().getFirstName());
			existingDepartment.getEmployee().setLastName(departmentManager.getEmployee().getLastName());
			existingDepartment.getEmployee().setBirthDate(departmentManager.getEmployee().getBirthDate());
			DepartmentManager updatedManager = departmentManagerService
					.updatebyempnoAnddeptnoAndfromdate(existingDepartment);

			return ResponseEntity.ok(updatedManager);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@Transactional
	@PutMapping("empNo/{empNo}/deptNo/{deptNo}")
	public ResponseEntity<DepartmentManager> updateDepartmentManager2(@PathVariable int empNo,
			@PathVariable String deptNo, @RequestBody DepartmentManager departmentManager) {
		DepartmentManager existingDepartment = departmentManagerService.getDepartmentManagerByEmpNoAndDeptNo(empNo,
				deptNo);
		if (existingDepartment != null) {
			existingDepartment.getEmployee().setFirstName(departmentManager.getEmployee().getFirstName());
			existingDepartment.getEmployee().setLastName(departmentManager.getEmployee().getLastName());
			existingDepartment.getEmployee().setBirthDate(departmentManager.getEmployee().getBirthDate());
			DepartmentManager updatedManager = departmentManagerService
					.updatebyempnoAnddeptnoAndfromdate(existingDepartment);

			return ResponseEntity.ok(updatedManager);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@Transactional
	@PutMapping("empNo/{empNo}/dept{deptNo}/fromdate/{fromDate}")
	public ResponseEntity<DepartmentManager> updateDepartmentManager(@PathVariable int empNo,
			@PathVariable("fromDate") Date fromDate,
			@PathVariable String deptNo,
			@RequestBody DepartmentManager departmentManager) {
		DepartmentManagerId de=new DepartmentManagerId();
		DepartmentManager existingDepartment = departmentManagerService.getDepartmentManagerByEmpNoAndDeptNoAndFromDate(empNo,
				deptNo,fromDate);
		if (existingDepartment != null) {
			existingDepartment.getEmployee().setFirstName(departmentManager.getEmployee().getFirstName());
			existingDepartment.getEmployee().setLastName(departmentManager.getEmployee().getLastName());
			existingDepartment.getEmployee().setBirthDate(departmentManager.getEmployee().getBirthDate());
			existingDepartment.getEmployee().setGender(departmentManager.getEmployee().getGender());
			existingDepartment.setFromDate(departmentManager.getFromDate());
			existingDepartment.setToDate(departmentManager.getToDate());
			
			DepartmentManager updatedManager = departmentManagerService
					.updatebyempnoAnddeptnoAndfromdate(existingDepartment);

			return ResponseEntity.ok(updatedManager);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@Transactional
	@PutMapping("empNo/{empNo}/fromdate/{fromDate}")
	public ResponseEntity<DepartmentManager> updateDepartmentManager(@PathVariable int empNo,
			@PathVariable("fromDate") Date fromDate,
			
			@RequestBody DepartmentManager departmentManager) {
		DepartmentManager existingDepartment = departmentManagerService.getDepartmentManagerByEmpNoAndFromDate(empNo,
				fromDate);
		if (existingDepartment != null) {
			existingDepartment.getEmployee().setFirstName(departmentManager.getEmployee().getFirstName());
			existingDepartment.getEmployee().setLastName(departmentManager.getEmployee().getLastName());
			existingDepartment.getEmployee().setBirthDate(departmentManager.getEmployee().getBirthDate());
			existingDepartment.getEmployee().setGender(departmentManager.getEmployee().getGender());
			DepartmentManager updatedManager = departmentManagerService
					.updatebyempnoAnddeptnoAndfromdate(existingDepartment);

			return ResponseEntity.ok(updatedManager);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/empno/{empNo}/deptno/{deptNo}/fromdate/{fromDate}")
	public ResponseEntity<Void> deleteDepartmentManagerByEmpNoAndDeptNoAndFromDate(@PathVariable int empNo,
			@PathVariable String deptNo, @PathVariable Date fromDate) {
		departmentManagerService.deleteByEmpNoAndDeptNoAndFromDate(empNo, fromDate, deptNo);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/empno/{empNo}/deptno/{deptNo}")
	public ResponseEntity<Void> deleteDepartmentManagerByEmpNoAndDeptNo(@PathVariable int empNo,
			@PathVariable String deptNo) {
		departmentManagerService.deleteByEmpNoAndDeptNo(empNo, deptNo);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/empno/{empNo}/fromdate/{fromDate}")
	public ResponseEntity<Void> deleteDepartmentManagerByEmpNoAndFromDate(@PathVariable int empNo,
			@PathVariable Date fromDate) {
		departmentManagerService.deleteByEmpNoAndFromDate(empNo, fromDate);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/deptno/{deptNo}/fromdate/{fromDate}")
	public ResponseEntity<Void> deleteDepartmentManagerByDeptNoAndFromDate(@PathVariable String deptNo,
			@PathVariable Date fromDate) {
		departmentManagerService.deleteByDeptNoAndFromDate(deptNo, fromDate);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/deptno/{deptNo}")
	public ResponseEntity<Void> deleteDepartmentManagerByDeptNo(@PathVariable String deptNo) {
		departmentManagerService.deleteByDeptNo(deptNo);
		return ResponseEntity.noContent().build();
	}
	

	@PostMapping("/DepartmentManager/{empNo}")
	@Operation(summary = "Add an existing Employee to DepartmentManager")
	public ResponseEntity<DepartmentManager> addExistingEmployeeToDepartmentManager(
	        @PathVariable int empNo,
	        @RequestBody DepartmentManager departmentManager) {
	    Employee employee = employeeService.getEmployeeById(empNo);
	    DepartmentEmployee departmentEmployee = departmentEmployeeServices.getDepartEmployeeEmpNo(empNo);
	    if (employee == null || departmentEmployee == null) {
	        throw new NotFoundException("Employee or DepartmentEmployee not found");
	    }
	    departmentManager.setEmployee(employee);
	    departmentManager.setDepartment(departmentEmployee.getDepartment());

	    DepartmentManager createdDepartmentManager = departmentManagerService.saveDepartmentManager(departmentManager);

	    departmentEmployeeServices.deleteByEmpNo(empNo);
	    return ResponseEntity.status(HttpStatus.CREATED).body(createdDepartmentManager);
	}

	
}
