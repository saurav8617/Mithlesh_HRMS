package com.hrms.entities;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*
 * Write JPA query to print data in descending order in hireDate wise
 * 
 *  @Query("SELECT e FROM Employee e ORDER BY e.hireDate DESC")
 * 
 * */

@Entity
@Table(name = "employees")

public class Employee {
	
	@Id
	@Column(name = "emp_no", nullable=false)
	private int empNo;
	
	@Column(name = "birth_date", nullable=false)
	private Date birthDate;
	
	@Column(name = "first_name", nullable=false, length=14)
	private String firstName;
	
	@Column(name = "last_name", nullable=false, length=16)
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private String role;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable=true)
	private Gender gender;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = "USER";
	}





	@Column(name = "hire_date", nullable=false)
	private Date hireDate;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<DepartmentManager> departmentManager;


    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DepartmentEmployee> departmentEmployee;
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Salaries> salary;
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Titles> titles;


	public Employee(Employee employee, List<Salaries> salaries, List<Titles> titles2, Departments department) {
		super();
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee(String firstName, String lastName, String email, String password, Gender gender, Date hireDate,
			List<Salaries> salary, List<Titles> titles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.hireDate = hireDate;
		this.salary = salary;
		this.titles = titles;
	}
	
	
	
	

	public Employee() {
		super();
	}
}
