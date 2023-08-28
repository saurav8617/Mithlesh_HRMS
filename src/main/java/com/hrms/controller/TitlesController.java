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

import com.hrms.entities.Titles;
import com.hrms.exceptions.InvalidDataException;
import com.hrms.services.TitleService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/titles")
@CrossOrigin
public class TitlesController {
	
	@Autowired
    private TitleService titleService;

 

    @GetMapping("/all")
    @Operation(summary = "Fetch all titles objects")
    public List<Titles> findAllSalary() {
        return titleService.getTitles();
    }


    @PostMapping("/add")
    @Operation(summary = "Add new titles object in DB")
    public ResponseEntity<Titles> addNewTitles(@RequestBody Titles titles) {
        Titles createdTitle = titleService.addTitle(titles);
        if (createdTitle != null) {
            return ResponseEntity.status(HttpStatus.OK).body(createdTitle);
        } else {
            throw new InvalidDataException("Validation Failed");
        }
    }
    @GetMapping("/empno/{empno}/fromdate/{fromdate}/title/{title}")
    @Operation(summary = "Search titles by from date, empno, title")
    public List<Titles> findByEmpNoAndFromDateAndTitle(@PathVariable("empno") int empNo,
            @PathVariable("fromdate") Date fromDate,
            @PathVariable("title") String title) {
        return titleService.getTitleByEmpNoAndDeptNo(empNo, fromDate, title);
    }

 

    @GetMapping("/title/{title}")
    @Operation(summary = "Fetch all titles objects by title")
    public List<Titles> findAllByTitle(@PathVariable("title") String title) {
        return titleService.getAllByTitle(title);
    }

 

    @GetMapping("/fromdate/{fromDate}")
    @Operation(summary = "Fetch all titles objects by from date")
    public List<Titles> findByFromDate(
            @PathVariable("fromDate") Date fromDateParam) {
        return titleService.findAllByFromDate(fromDateParam);
    }

 

    @GetMapping("title/{title}/fromdate/{fromdate}")
    @Operation(summary = "Fetch all titles objects by title and fromdate")
    public List<Titles> findByEmpNoAndFromDateAndTitle(
            @PathVariable("fromdate") Date fromDate,
            @PathVariable("title") String title) {
        return titleService.getTitleByTitleAndFromDate(fromDate, title);
    }

 

    @GetMapping("/empno/{empno}/title/{title}")
    @Operation(summary = "Fetch all titles objects by empno and fromdate")
    public List<Titles> findByEmpNoAndAndTitle(@PathVariable("empno") int empNo, @PathVariable("title") String title) {
        return titleService.getTitleByTitleAndFromDate(empNo, title);
    }
    
    @GetMapping("/empno/{empno}")
    @Operation(summary = "Fetch all titles objects by empno and fromdate")
    public List<Titles> findByEmpNo(@PathVariable("empno") int empNo) {
        return titleService.getTitleByEmpNo(empNo);
    }

 

    @GetMapping("/empno/{empno}/fromdate/{fromdate}")
    @Operation(summary = "Fetch all titles objects by empno and title")
    public List<Titles> findByEmpNoAndFromDate(@PathVariable("empno") int empNo,
            @PathVariable("fromdate") Date fromDate) {
        return titleService.getTitleByEmpNoAndFromDate(empNo, fromDate);
    }
    
	@PutMapping("/empno/{empNo}/fromdate/{fromDate}/title/{title}")
    public ResponseEntity<Void> updateTitle(
            @RequestBody Titles titl,
            @PathVariable int empNo,
            @PathVariable Date fromDate,
            @PathVariable String title) {
        Titles existingTitle = titleService.findByEmpNoAndFromDateAndTitle(empNo, fromDate, title);
        if (existingTitle != null) {
        	existingTitle.setTitle(titl.getTitle());
        	existingTitle.setTitle(titl.getTitle());
        	existingTitle.setToDate(titl.getToDate());
            titleService.updateByEmpNo(existingTitle);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
	
	@PutMapping("/empno/{empNo}")
    public ResponseEntity<Void> updateTitle(
            @RequestBody Titles titl,
            @PathVariable int empNo) {
        Titles existingTitle = titleService.getByEmpNo(empNo);
        System.out.println("IN");
        if (existingTitle != null) {
        	existingTitle.setTitle(titl.getTitle());
        	existingTitle.setToDate(titl.getToDate());
            titleService.updateByEmpNo(existingTitle);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
	@PutMapping("/fromdate/{fromDate}")
    public ResponseEntity<Void> updateTitle(
            @RequestBody Titles titl,
            @PathVariable Date fromDate) {
        Titles existingTitle = titleService.findByFromDates(fromDate);
        if (existingTitle != null) {
        	existingTitle.setTitle(titl.getTitle());
        	existingTitle.setToDate(titl.getToDate());
            titleService.updateByEmpNo(existingTitle);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
	@PutMapping("/title/{title}")
    public ResponseEntity<Void> updateTitle(
            @RequestBody Titles titl,
            @PathVariable String title) {
        Titles existingTitle = titleService.getbytitle(title);
        if (existingTitle != null) {
        	existingTitle.setTitle(titl.getTitle());
        	existingTitle.setToDate(titl.getToDate());
            titleService.updateByEmpNo(existingTitle);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  
	@DeleteMapping("/empno/{empNo}/fromdate/{fromDate}/title/{title}")
    public ResponseEntity<String> deleteTitleByEmpNoFromDateAndTitle(
    		@RequestParam int empNo,
            @RequestParam Date fromDate,
            @RequestParam("title") String title) {
		titleService.deleteByEmpNoFromDateAndTitle(empNo, fromDate, title);
        return ResponseEntity.ok("Title deleted successfully");
    }
	
	@DeleteMapping("/empno/{empNo}")
    public ResponseEntity<String> deleteTitleByEmpNo(
    		@PathVariable int empNo) {
		titleService.deleteByEmpNo(empNo);
        return ResponseEntity.ok("Title deleted successfully");
    }
	
	@DeleteMapping("/fromdate/{fromDate}")
    public ResponseEntity<String> deleteTitleByEmpNoFromDateAndTitle(
            @RequestParam Date fromDate) {
		titleService.deleteByFromDate(fromDate);
        return ResponseEntity.ok("Title deleted successfully");
    }
	
	@DeleteMapping("/title/{title}")
    public ResponseEntity<String> deleteByTitle(
            @RequestParam("title") String title) {
		titleService.deleteByTitle(title);
        return ResponseEntity.ok("Title deleted successfully");
    }	
	
}
