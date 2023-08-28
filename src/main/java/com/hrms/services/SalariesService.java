package com.hrms.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.entities.Salaries;
import com.hrms.repository.SalariesRepository;

 

@Service
public class SalariesService {

 

    @Autowired
    private SalariesRepository salaryRepository;

 

    public List<Salaries> getSalary() {
        return salaryRepository.findAll();
    }

 

    public List<Salaries> getSalaryByFromDate(Date fromDate) {
        return salaryRepository.findAllByFromDate(fromDate);
    }

 

    public List<Salaries> getSalariesByEmployee(int empNo) {
        return salaryRepository.findSalaryByEmployee(empNo);
    }


    
    public List<Salaries> getSalaryByRange(int minSalary, int maxSalary) {
        return salaryRepository.findAllBySalaryBetween(minSalary, maxSalary);
    }

    public Salaries getSalaryByEmpNoandFromDate(int empNo, Date fromDate) {
      return salaryRepository.findbyempnoandfromdate(empNo, fromDate);
  }



    public Salaries addSalary(Salaries salary) {
         return salaryRepository.save(salary);

    }
    
    public Salaries updateSalary(Salaries salary) {
        return salaryRepository.save(salary);
    }

    
    public Salaries getSalaryByFromDates(Date fromDate) {
        return salaryRepository.findbyfromdate(fromDate);
    }

 

    public Salaries getSalariesByEmployees(int empNo) {
        return salaryRepository.findsSalaryByEmployee(empNo);
    }
    
    @Transactional
    public void deleteByEmpNoAndFromDate(int empNo, Date fromDate) {
        salaryRepository.deleteByEmployeeEmpNoAndFromDate(empNo, fromDate);
    }
    
    @Transactional
    public void deleteByFromDate(Date fromDate) {
        salaryRepository.deleteByFromDate(fromDate);
    }
    
    @Transactional
    public void deleteByEmpNo(int empNo) {
        salaryRepository.deleteByEmployeeEmpNo(empNo);
    }

    
}
