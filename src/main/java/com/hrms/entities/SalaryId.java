package com.hrms.entities;

import java.io.Serializable;
import java.sql.Date;

public class SalaryId implements Serializable{
	
	private Date fromDate;
    private Employee employee;
    
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public SalaryId(Date fromDate, Employee employee) {
		super();
		this.fromDate = fromDate;
		this.employee = employee;
	}
	public SalaryId() {
		super();
	}
    
    
    
    

}
