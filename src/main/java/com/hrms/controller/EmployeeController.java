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
import org.springframework.web.bind.annotation.RestController;

import com.hrms.entities.CreateEmployeeRequest;
import com.hrms.entities.DepartmentEmployee;
import com.hrms.entities.Departments;
import com.hrms.entities.Employee;
import com.hrms.entities.Gender;
import com.hrms.entities.Salaries;
import com.hrms.entities.Titles;
import com.hrms.exceptions.InvalidDataException;
import com.hrms.exceptions.NotFoundException;
import com.hrms.services.DepartmentEmployeeServices;
import com.hrms.services.DepartmentSerives;
import com.hrms.services.EmployeeService;
import com.hrms.services.SalariesService;
import com.hrms.services.TitleService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin
public class EmployeeController {

	@Autowired
	public EmployeeService employeeService;

	@Autowired
	private DepartmentEmployeeServices departmentEmployeeService;

	@Autowired
	private SalariesService salaryService;

	@Autowired
	private TitleService titleService;
	
	@Autowired
    private DepartmentSerives departmentServices;

 

	
	@GetMapping("/all")
	@Operation(summary = "Fetch all employees.")
	public List<Employee> findAllEmployees() {
		return employeeService.getEmployee();
	}

	@GetMapping("id/{id}")
	@Operation(summary = "Fetch an employees by employee number")
	public Employee findEmployeeById(@PathVariable int id) { // Change
		if (employeeService.getEmployeeById(id) != null)
			return employeeService.getEmployeeById(id);
		else
			throw new NotFoundException("Employee Id is not correct! Please check again");
	}

	@GetMapping("/firstname/{firstName}")
	@Operation(summary = "Search Employee by First Name")
	public List<Employee> findEmployeeByFirstName(@PathVariable("firstName") String firstName) {
		if (employeeService.getEmployeeByFirstName(firstName) != null)
			return employeeService.getEmployeeByFirstName(firstName);
		else
			throw new NotFoundException("First Name is not correct! Please Check again.");
	}
	
	

	@GetMapping("/lastname/{lastName}")
	@Operation(summary = "Search Employee by Last Name")
	public List<Employee> findEmployeeByLastName(@PathVariable("lastName") String lastName) {
		return employeeService.getEmployeeByLastName(lastName);
	}
	
	@GetMapping("/email/{email}")
	@Operation(summary = "Search Employee by Last Name")
	public List<Employee> findEmployeeByEmail(@PathVariable("email") String email) {
		return employeeService.getEmployeeByemail(email);
	}

	@GetMapping("/gender/{gender}")
	@Operation(summary = "Search employees by gender")
	public List<Employee> findByGender(@PathVariable("gender") String gender) {
		Gender g = Gender.valueOf(gender);
		return employeeService.getEmployeesByGender(g);
	}
	
	  @GetMapping("/gendercount/{gender}")
	    public int getGenderCount(@PathVariable("gender") String gender) {
	        Gender g = Gender.valueOf(gender);
	        return employeeService.getGenderCount(g);
	    }
	  
	  @GetMapping("/awarded-employees/{yearsOfExperience}")
	    public List<Employee> getAwardedEmployeesWithExperience(@PathVariable("yearsOfExperience") int yearsOfExperience) {
	        return employeeService.getAwardedEmployeesWithExperience(yearsOfExperience);
	    }

	@GetMapping("/hiredate/{hiredate}")
	@Operation(summary = "Search employees by  hire date")
	public List<Employee> findEmployeesByHireDate(
			@PathVariable("hiredate")Date hireDate) {
		return employeeService.getEmployeesByHireDate(hireDate);
	}

	@GetMapping("/birthdate/{birthdate}")
	@Operation(summary = "Search employees by  birth date")
	public List<Employee> findEmployeesByBirthDate(
			@PathVariable("birthdate")  Date birthDate) {
		return employeeService.getEmployeesByBirthDate(birthDate);
	}

	@GetMapping("/value")
	public List<Employee> getEmp() {
		return employeeService.getAllEmployeesSortedByHireDateDesc();
	}
	
	@GetMapping("/joined-last-10-years")
    public List<Employee> getEmployeesJoinedLast10Years() {
        return employeeService.getEmployeesJoinedLast10Years();
    }
	
	 @GetMapping("/joined-last-10-years/count")
	    public int getCountEmployeesJoinedLast10Years() {
	        return employeeService.getCountEmployeesJoinedLast10Years();
	    }
	 
	    @GetMapping("/joined-after-year/{year}")
	    public List<Employee> getEmployeesJoinedAfter(@PathVariable("year") int year) {
	        return employeeService.getEmployeesJoinedAfter2005(year);
	    }
	    
	    @GetMapping("/joined-after-year/count{year}")
	    public int getCountEmployeesJoinedAfter(@PathVariable("year") int year) {
	        return employeeService.getCountEmployeesJoinedAfter2005(year);
	    }

	 
	@PostMapping("/add")
	@Operation(summary = "Add new employee object in DB")
	public ResponseEntity<String> addEmployeeC(@RequestBody Employee employee) {
		Employee response = employeeService.addEmployee(employee);
		if (response != null)
			return ResponseEntity.ok().body("{\"message\": \"New employee added successfully\"}");
		else
			throw new InvalidDataException("Validation Failed: Invalid entry.Please Check again");
	}


	@PutMapping("/lastname/{empno}")
	@Operation(summary = "Update Last Name of an employee given empno")
	public ResponseEntity<Employee> updateEmployeeByLastNameC(@PathVariable("empno") int empNo,
			@RequestBody Employee employee) {
		Employee emp = employeeService.updateEmployee(employee, empNo);
		if (emp != null)
			return new ResponseEntity<Employee>(emp, HttpStatus.OK);
		else
			throw new NotFoundException("Invalid Id: Unable to update the lastname");
	}

	@PutMapping("/Updateemployee/{empno}")
	@Operation(summary = "Update first Name of an employee given empno")
	public ResponseEntity<Employee> updateEmployeeByEmpNo(@PathVariable("empno") int empNo,
			@RequestBody Employee employee) {
		Employee emp = employeeService.updateEmployee(employee, empNo);

		if (emp != null)
			return new ResponseEntity<Employee>(emp, HttpStatus.OK);
		else
			throw new NotFoundException("Invalid Id: Unable to update the firstname");
	}
	
	@PutMapping("sign_up/empno/{empNo}")
    public ResponseEntity<Employee> signupemailandpassword(
            @RequestBody Employee employee,
            @PathVariable("empNo") int empNo) {
		Employee existingDetails = employeeService.getEmployeeById(empNo);
        
        if (existingDetails != null) {
        	existingDetails.setEmail(employee.getEmail());
        	existingDetails.setPassword(employee.getPassword());
         
        	Employee updateEmailPassword = employeeService.upadateEmailAndPassword(existingDetails);
            return ResponseEntity.ok(updateEmailPassword);
        }
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }	
	
	@PutMapping("/hiredate/{empno}")
	@Operation(summary = "Update hiredate for given empno")
	public Employee updateEmployeeByHireDateC(@PathVariable("empno") int empNo, @RequestBody Employee employee) {
		return employeeService.updateEmployeeByHireDate(employee, empNo);
	}

	@PutMapping("/birthdate/{empno}")
	@Operation(summary = "Update birthdate for given empno")
	public Employee updateEmployeeByBirthDateC(@PathVariable("empno") int empNo, @RequestBody Employee employee) {
		return employeeService.updateEmployeeBirthDate(employee, empNo);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<Employee> loginEmployee(@RequestBody Employee request) {
	    Employee employee = employeeService.findByEmailAndPassword(request.getEmail(), request.getPassword());
	    if (employee != null) {
	        return ResponseEntity.ok(employee);
	    } else {
	        return ResponseEntity.badRequest().build();
	    }
	}
	
	@PutMapping("/{empNo}")
    public ResponseEntity<Employee> updateEmployeeProfile(@PathVariable int empNo, @RequestBody Employee request) {
        Employee existingEmployee = employeeService.findByempNo(empNo).orElse(null);
        if (existingEmployee != null) {
            if (!existingEmployee.getEmail().equals(request.getEmail())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Return forbidden status if not authorized
            }
            existingEmployee.setFirstName(request.getFirstName());
            existingEmployee.setLastName(request.getLastName());
            Employee updatedEmployee = employeeService.updateemployee(existingEmployee);
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	@GetMapping("/hiredate/{startYear}/{endYear}")
	public ResponseEntity<List<Employee>> getEmployeesByHireDateRange(
	        @PathVariable("startYear") int startYear,
	        @PathVariable("endYear") int endYear
	) {
	    List<Employee> employees = employeeService.getEmployeesByHireDateRange(startYear, endYear);
	    return ResponseEntity.ok(employees);
	}
	
	@PostMapping("/email/{email}")
	public int findByEmail(@PathVariable String email) {
		return employeeService.getIdByEmail(email);
	}
	

	@DeleteMapping("deleteemployeebyempNo/{empNo}")
	public ResponseEntity<Void> deleteEmployeeByEmpNo(@PathVariable int empNo) {
	    employeeService.deleteEmployeeByEmpNo(empNo);
        return ResponseEntity.noContent().build();

	}

	
    @PostMapping("/create-employee")
    public ResponseEntity<String> createEmployee(@RequestBody CreateEmployeeRequest request) {
        try {
            Employee employee = request.getEmployee();
            Departments department=request.getDepartment();
            DepartmentEmployee departmentEmployee = request.getDepartmentEmployee();
            Salaries salary = request.getSalary();
            Titles title = request.getTitle();

            Employee savedEmployee = employeeService.addEmployee(employee);
            Departments savedDepartment= departmentServices.addDepartment(department);
      

            departmentEmployee.setEmployee(savedEmployee);
            departmentEmployee.setDepartment(savedDepartment);
            DepartmentEmployee savedDepartmentEmployee = departmentEmployeeService.saveDepartmentEmployee(departmentEmployee);

            // Save Salary
            salary.setEmployee(savedEmployee);
            Salaries savedSalary = salaryService.addSalary(salary);
            // Save Title
            title.setEmployee(savedEmployee);
            Titles savedTitle = titleService.addTitle(title);
            String message = "Employee, DepartmentEmployee, Salary, and Title created successfully";
            return ResponseEntity.ok().body("{\"message\": \"" + message + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error creating employee: " + e.getMessage() + "\"}");
        }
    }
    

    @PutMapping("/password/empno/{empno}/email/{email}")
        @Operation(summary = "Forgot Password")
        public Employee forgotPasswordC(@PathVariable("empno") int empNo, @PathVariable("email") String email, @RequestBody Employee employee) {
            System.out.println("email in Controller"+ email);
            System.out.println("password in Controller "+ employee.getPassword());
            return employeeService.forgotPassword(empNo, email, employee);    
        }
}
