package com.hrms.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrms.entities.Titles;
import com.hrms.repository.TitlesRepository;

@Service
public class TitleService {
	
	@Autowired
    private TitlesRepository titlesRepository;

 

    public List<Titles> getTitles() {
        return titlesRepository.findAll();
    }

 

    public Titles addTitle(Titles title) {
        return titlesRepository.save(title);
    }

    public List<Titles> getTitleByEmpNoAndDeptNo(int empNo, Date fromDate, String title) {
        return titlesRepository.findByTypeId(empNo, fromDate, title);
    }
    
    public List<Titles> getTitleByEmpNo(int empNo) {
        return titlesRepository.findByempNo(empNo);
    }

 

    public List<Titles> getAllByTitle(String title) {
        return titlesRepository.findByTitles(title);
    }
 

    public List<Titles> findAllByFromDate(Date fromDate) {
        return titlesRepository.findByFromDate(fromDate);
    }

 

    public List<Titles> getTitleByTitleAndFromDate(Date fromDate, String title) {
        return titlesRepository.findByFromDateAndTitle(fromDate, title);
    }

 

    public List<Titles> getTitleByTitleAndFromDate(int empNo, String title) {
        return titlesRepository.findByEmpnoAndTitle(empNo, title);
    }

 

    public List<Titles> getTitleByEmpNoAndFromDate(int empNo, Date fromDate) {
        return titlesRepository.findByFromDateAnDempNo(empNo, fromDate);
    }

	public Titles findByEmpNoAndFromDateAndTitle(int empNo, Date fromDate, String title) {
        return titlesRepository.findByEmployee_EmpNoAndFromDateAndTitle(empNo, fromDate, title);
    }
	 
	public Titles getByEmpNo(int empNo) {
		return titlesRepository.findByEmployee_EmpNo(empNo);
	}
	
	public Titles findByFromDates(Date fromDate) {
		return titlesRepository.findByFromDate1(fromDate);
	}
	
	public Titles getbytitle(String title) {
		return titlesRepository.findByTitle(title);
	}
	
	public Titles updateByEmpNo(Titles titles) {
	    return titlesRepository.save(titles);
	}

	@Transactional
	 public void deleteByEmpNoFromDateAndTitle(int empNo, Date fromDate, String title) {
	        titlesRepository.deleteByEmployee_EmpNoAndFromDateAndTitle(empNo, fromDate, title);
	    }
	@Transactional
	 public void deleteByEmpNo(int empNo) {
	        titlesRepository.deleteByEmployee_EmpNo(empNo);
	    }
	@Transactional
	 public void deleteByFromDate(Date fromDate) {
	        titlesRepository.deleteByFromDate(fromDate);
	    }
	@Transactional
	 public void deleteByTitle(String title) {
	        titlesRepository.deletebyTitle(title);
	    }
	
}
