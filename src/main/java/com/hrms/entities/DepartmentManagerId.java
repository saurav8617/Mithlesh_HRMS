package com.hrms.entities;

import java.io.Serializable;

public class DepartmentManagerId implements Serializable {
	
	private Departments department;
	private Employee employee;
	public Departments getDepartment() {
		return department;
	}
	public void setDepartment(Departments department) {
		this.department = department;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public DepartmentManagerId(Departments department, Employee employee) {
		super();
		this.department = department;
		this.employee = employee;
	}
	public DepartmentManagerId() {
		super();
	}
	
	
	
	
	
}
