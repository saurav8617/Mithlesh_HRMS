package com.hrms.controller;

import java.sql.Date;
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

import com.hrms.entities.Salaries;
import com.hrms.exceptions.InvalidDataException;
import com.hrms.services.SalariesService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/api/v1/salaries")
@CrossOrigin
public class SalariesController {

    @Autowired
    public SalariesService salaryService;

    @GetMapping("/all")
    @Operation(summary = "Fetch all salary objects")
    public List<Salaries> findAllSalary() {
        return salaryService.getSalary();
    }



    @GetMapping("/fromdate/{fromdate}")
    @Operation(summary = "Fetch all salary objects by from date")
    public List<Salaries> findSalaryByFromDate(
            @PathVariable("fromdate") Date fromDate) {
        return salaryService.getSalaryByFromDate(fromDate);
    }

 

    @GetMapping("/empno/{empno}")
    @Operation(summary = "Fetch all salary objects by empno")
    public List<Salaries> findSalaryByEmployee(@PathVariable("empno") int empNo) {
        return salaryService.getSalariesByEmployee(empNo);
    }

 

    @GetMapping("/salary/{minsalary}/{maxsalary}")
    @Operation(summary = "Fetch all salary objects by salary ")
    public List<Salaries> findSalaryByRange(@PathVariable("minsalary") int minSalary,
            @PathVariable("maxsalary") int maxSalary) {
        return salaryService.getSalaryByRange(minSalary, maxSalary);
    }
    
    @GetMapping("/empNo/{empNo}/fromDate/{fromDate}")
    public Salaries findSalaryByFromDate(@PathVariable("empNo") int empNo, @PathVariable("fromDate") Date fromDate) {
        return salaryService.getSalaryByEmpNoandFromDate(empNo, fromDate);
    }

 

    @PostMapping("/add")
    @Operation(summary = "Add new salary object in DB")
    public ResponseEntity<Salaries> addSalaryC(@RequestBody Salaries salary) {
        Salaries response = salaryService.addSalary(salary);
        if (response != null)
            return ResponseEntity.status(HttpStatus.OK).body(response);
        else
            throw new InvalidDataException("Validation Failed: ");
    }

    @PutMapping("/fromdate/{fromDate}")
    public ResponseEntity<Salaries> updateSalaryByEmpNoAndFromDate(
            @RequestBody Salaries salary,
            @PathVariable("fromDate") Date fromDate) {
        Salaries existingSalary = salaryService.getSalaryByFromDates(fromDate);
        
        if (existingSalary != null) {
            existingSalary.setSalary(salary.getSalary());
            existingSalary.setToDate(salary.getToDate());
            Salaries updatedSalary = salaryService.updateSalary(existingSalary);
            return ResponseEntity.ok(updatedSalary);
        }
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    

    @PutMapping("/empno/{empNo}/fromdate/{fromDate}")
    public ResponseEntity<Salaries> updateSalaryByEmpNoAndFromDate(
            @RequestBody Salaries salary,
            @PathVariable int empNo,
            @PathVariable("fromDate") Date fromDate) {
        Salaries existingSalary = salaryService.getSalaryByEmpNoandFromDate(empNo, fromDate);
        
        if (existingSalary != null) {
            existingSalary.setSalary(salary.getSalary());
            Salaries updatedSalary = salaryService.updateSalary(existingSalary);
            return ResponseEntity.ok(updatedSalary);
        }
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping("/empno/{empNo}")
    public ResponseEntity<Salaries> updateSalaryByEmpNoAndFromDate(
            @RequestBody Salaries salary,
            @PathVariable int empNo) {
        Salaries existingSalary = salaryService.getSalariesByEmployees(empNo);
        
        if (existingSalary != null) {
            existingSalary.setSalary(salary.getSalary());
            existingSalary.setToDate(salary.getToDate());
            Salaries updatedSalary = salaryService.updateSalary(existingSalary);
            return ResponseEntity.ok(updatedSalary);
        }
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    
	@DeleteMapping("/empno/{empNo}/fromdate/{fromDate}")
    public ResponseEntity<String> deleteTitleByEmpNofromDate(
    		@RequestParam int empNo,
            @RequestParam Date fromDate) {
		salaryService.deleteByEmpNoAndFromDate(empNo, fromDate);
        return ResponseEntity.ok("Title deleted successfully");
    }
	
	@DeleteMapping("/fromdate/{fromDate}")
    public ResponseEntity<String> deleteTitleByEmpNoFromDate(
            @RequestParam Date fromDate) {
		salaryService.deleteByFromDate(fromDate);
        return ResponseEntity.ok("Title deleted successfully");
    }
	
	@DeleteMapping("/empno/{empNo}")
    public ResponseEntity<String> deleteTitleByEmpNo(
    		@RequestParam int empNo) {
		salaryService.deleteByEmpNo(empNo);
        return ResponseEntity.ok("Title deleted successfully");
    }
    
    
    
}
    
