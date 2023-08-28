package com.hrms.entities;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="dept_emp")
@IdClass(DepartmentEmployeeId.class)
public class DepartmentEmployee {
	
	@Column(name = "from_date", nullable=false)
	private Date fromDate;
	
	@Column(name= "to_date", nullable=false)
	private Date toDate;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "emp_no")
	private Employee employee;
	
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dept_no")
	private Departments department;


	public Date getFromDate() {
		return fromDate;
	}


	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}


	public Date getToDate() {
		return toDate;
	}


	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public Departments getDepartment() {
		return department;
	}


	public void setDepartment(Departments department) {
		this.department = department;
	}


	public DepartmentEmployee(Date fromDate, Date toDate, Employee employee, Departments department) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.employee = employee;
		this.department = department;
	}


	public DepartmentEmployee() {
		super();
	}
	

	}
