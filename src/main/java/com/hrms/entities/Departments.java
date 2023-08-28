package com.hrms.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Departments {
	@Id
	@Column(name = "dept_no", length = 4, nullable=false)
	private String deptNo;
	
	@Column(name = "dept_name", length=40,nullable=false)
	private String deptName;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	private List<DepartmentManager> departmentManager;
	
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	private List<DepartmentEmployee> departmentEmployee;

	public String getDept_no() {
		return deptNo;
	}

	public void setDept_no(String dept_no) {
		this.deptNo = dept_no;
	}

	public String getDept_name() {
		return deptName;
	}

	public void setDept_name(String dept_name) {
		this.deptName = dept_name;
	}

	public Departments() {
		super();
	}

	public Departments(String deptNo, String deptName, List<DepartmentManager> departmentManager,
			List<DepartmentEmployee> departmentEmployee) {
		super();
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.departmentManager = departmentManager;
		this.departmentEmployee = departmentEmployee;
	}


}
