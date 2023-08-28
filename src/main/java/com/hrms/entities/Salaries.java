package com.hrms.entities;


import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(SalaryId.class)

public class Salaries {

	@Id
	@Column(name = "from_date", nullable = false)
	private Date fromDate;

	@Column(name = "salary", nullable = false)
	private int salary;

	@Column(name = "to_date", nullable = false)
	private Date toDate;

	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "emp_no")
	private Employee employee;

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date from_date) {
		this.fromDate = from_date;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date to_date) {
		this.toDate = to_date;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Salaries() {
		super();
	}

	public Salaries(Date fromDate, int salary, Date toDate, Employee employee) {
		super();
		this.fromDate = fromDate;
		this.salary = salary;
		this.toDate = toDate;
		this.employee = employee;
	}
	

}
