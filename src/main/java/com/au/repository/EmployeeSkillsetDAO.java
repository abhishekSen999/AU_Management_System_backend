package com.au.repository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.au.domain.Employee;
import com.au.domain.EmployeeMapper;
import com.au.domain.Skill;
import com.au.domain.SkillMapper;

@Component
public class EmployeeSkillsetDAO {
	
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	SkillMapper skillMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	public Collection<Skill> getAllSkillOfEmployeeWithId(long id)
	{
		String sql = "select skill.skill_id, skill.skill_name from skill join employee_skillset  on  employee_skillset.skill_id =  skill.skill_id where employee_skillset.emp_id = ?" ;
		
		Collection<Skill>  skillSet = jdbcTemplate.query(sql,new Object[] {id}, skillMapper) ;
		
		return skillSet;
	}
	
	public Collection<Employee> getAllEmployeeWithSkillId(long id)
	{
		String sql = "select employee.emp_id , employee.name , employee.company_email , employee.personal_email , employee.location from employee join employee_skillset on employee_skillset.emp_id = employee.emp_id where employee_skillset.skill_id =?";
		
		Collection<Employee> employeeList = jdbcTemplate.query(sql,new Object[]{id}, employeeMapper);
		
		
		return employeeList;
		
	}
	
	
	
	
	
	

}
