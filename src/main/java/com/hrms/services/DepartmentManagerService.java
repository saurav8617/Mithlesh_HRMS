package com.hrms.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.entities.DepartmentManager;
import com.hrms.repository.DepartmentManagerRepository;


@Service
public class DepartmentManagerService {

	@Autowired
	private DepartmentManagerRepository departmentManagerRespository;

	public List<DepartmentManager> getDepartmentManager() {
		return departmentManagerRespository.findAll();
	}

	public DepartmentManager getDepartmentManagersByEmpNoAndDeptNo(int empNo, String deptNo) {
		return departmentManagerRespository.findByEmployee_EmpNoAndDepartment_DeptNo(empNo, deptNo);
	}

	public List<DepartmentManager> findByDeptNoAndFromDate(String deptNo, Date fromDate) {
		return departmentManagerRespository.findByDepartmentDeptNoAndFromDate(deptNo, fromDate);
	}
	
	public List<DepartmentManager> findByDeptNo(String deptNo) {
		return departmentManagerRespository.findByDepartmentDeptNo(deptNo);
	}
	
	public List<DepartmentManager> findByempNo(int empNo) {
		return departmentManagerRespository.findByEmployee_EmpNo(empNo);
	}



	public DepartmentManager findByEmpNoAndFromDate(int empNo, Date fromDate) {
		return departmentManagerRespository.findByEmployee_EmpNoAndFromDate(empNo, fromDate);
	}

	public DepartmentManager getDepartmentManagersByEmpNoAndDeptNoAndFromDate(int empNo, String deptNo,
			Date fromdate) {
		return departmentManagerRespository.findByEmployee_EmpNoAndDepartment_DeptNoAndFromDate(empNo, deptNo, fromdate);
	}

	public DepartmentManager saveDepartmentManager(DepartmentManager departmentManager) {
		if (departmentManager != null)
			return departmentManagerRespository.save(departmentManager);
		else
			return null;
	}
	
	public List<Object[]> getEmployeesFromDate(Date fromDate) {
        return departmentManagerRespository.findEmployeesFromDate(fromDate);
    }

	public DepartmentManager updateByEmpNoAndDeptNo(DepartmentManager departmentManager) {
		if (departmentManager != null)
			return departmentManagerRespository.save(departmentManager);
		else
			return null;
	}

	public DepartmentManager updateByEmpNoAndFromDate(DepartmentManager departmentManager) {
		if (departmentManager != null)
			return departmentManagerRespository.save(departmentManager);
		else
			return null;
	}

	public DepartmentManager updateByDeptNoAndFromDate(DepartmentManager departmentManager) {
		if (departmentManager != null)
			return departmentManagerRespository.save(departmentManager);
		else
			return null;
	}

	public DepartmentManager updateByEmpNoAndDeptNoAndFromDate(DepartmentManager departmentManager) {
		if (departmentManager != null)
			return departmentManagerRespository.save(departmentManager);
		else
			return null;
	}

	public DepartmentManager updatebydeptnoAndfromdate(DepartmentManager departmentManager) {
		return departmentManagerRespository.save(departmentManager);
	}

	public DepartmentManager updatebyempnoAnddeptnoAndfromdate(DepartmentManager departmentManager) {
		return departmentManagerRespository.save(departmentManager);
	}

	@Transactional
	public void deleteByEmpNoAndDeptNoAndFromDate(int empNo, Date fromDate, String deptNo) {
		departmentManagerRespository.deleteByEmpNoAndDeptNoAndFromDate(empNo, deptNo, fromDate);
	}

	@Transactional
	public void deleteByEmpNoAndDeptNo(int empNo, String deptNo) {
		departmentManagerRespository.deleteByEmpNoAndDeptNo(empNo, deptNo);
	}

	@Transactional
	public void deleteByEmpNoAndFromDate(int empNo, Date fromDate) {
		departmentManagerRespository.deleteByEmpNoAndFromDate(empNo, fromDate);
	}

	@Transactional
	public void deleteByDeptNoAndFromDate(String deptNo, Date fromDate) {
		departmentManagerRespository.deleteByDeptNoAndFromDate(deptNo, fromDate);
	}
	
	@Transactional
	public void deleteByDeptNo(String deptNo) {
		departmentManagerRespository.deleteByDeptNo(deptNo);
	}

	public DepartmentManager getDepartmentManagerByEmpNoAndDeptNoAndFromDate(int empNo, String deptNo, Date fromDate) {
		return departmentManagerRespository.findByEmployee_EmpNoAndDepartment_DeptNoAndFromDate(empNo, deptNo,
				fromDate);
	}

	public DepartmentManager getDepartmentDeptNoAndFromDate(String deptNo, Date fromDate) {
		return departmentManagerRespository.findByDepartment_DeptNoAndFromDate(deptNo, fromDate);
	}

	public DepartmentManager getDepartmentManagerByEmpNoAndDeptNo(int empNo, String deptNo) {
		return departmentManagerRespository.findByEmployee_EmpNoAndDepartment_DeptNo(empNo, deptNo);
	}

	public DepartmentManager getDepartmentManagerByEmpNoAndFromDate(int empNo, Date fromDate) {
		return departmentManagerRespository.findByEmployee_EmpNoAndFromDate(empNo, fromDate);
	}

}
