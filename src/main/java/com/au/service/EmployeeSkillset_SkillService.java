package com.au.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.au.domain.Skill;
import com.au.repository.EmployeeSkillset_SkillDAO;

@Component
public class EmployeeSkillset_SkillService {
	
	
	@Autowired
	EmployeeSkillset_SkillDAO employeeSkillset_SkillDao;
	
	EmployeeSkillset_SkillDAO getEmployeeSkillset_SkillDao() {
		return employeeSkillset_SkillDao;
	}
	
	public List<Skill>   getAllSkillOfEmployeeWithId( long emp_id)
	{
		 return  getEmployeeSkillset_SkillDao().getAllSkillOfEmployeeWithId(emp_id);
				
	}
	
	
	
	

}
