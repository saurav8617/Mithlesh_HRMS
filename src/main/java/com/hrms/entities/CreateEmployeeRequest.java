package com.hrms.entities;

public class CreateEmployeeRequest {
	 private Employee employee;
	    private DepartmentEmployee departmentEmployee;
	    private Salaries salary;
	    private Titles title;
	    private Departments department;
	    
		public Departments getDepartment() {
			return department;
		}
		public void setDepartment(Departments department) {
			this.department = department;
		}
		public CreateEmployeeRequest(Employee employee, DepartmentEmployee departmentEmployee, Salaries salary,
				Titles title, Departments department) {
			super();
			this.employee = employee;
			this.departmentEmployee = departmentEmployee;
			this.salary = salary;
			this.title = title;
			this.department = department;
		}
		public CreateEmployeeRequest() {
			super();
		}
		public Employee getEmployee() {
			return employee;
		}
		public void setEmployee(Employee employee) {
			this.employee = employee;
		}
		public DepartmentEmployee getDepartmentEmployee() {
			return departmentEmployee;
		}
		public void setDepartmentEmployee(DepartmentEmployee departmentEmployee) {
			this.departmentEmployee = departmentEmployee;
		}
		public Salaries getSalary() {
			return salary;
		}
		public void setSalary(Salaries salary) {
			this.salary = salary;
		}
		public Titles getTitle() {
			return title;
		}
		public void setTitle(Titles title) {
			this.title = title;
		}
	    
	    

}
