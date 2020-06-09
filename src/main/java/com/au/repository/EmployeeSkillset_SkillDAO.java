package com.au.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.au.domain.Employee;
import com.au.domain.EmployeeMapper;
import com.au.domain.Skill;
import com.au.domain.SkillMapper;

@Component
public class EmployeeSkillset_SkillDAO {
	
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	SkillMapper skillMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	public List<Skill> getAllSkillOfEmployeeWithId(long emp_id)
	{
		String sql = "select skill.skill_id, skill.skill_name from skill join employee_skillset  on  employee_skillset.skill_id =  skill.skill_id where employee_skillset.emp_id = ?" ;
		
		List<Skill>  skillList = jdbcTemplate.query(sql,new Object[] {emp_id}, skillMapper) ;
		
		return skillList;
	}
	

	
	
	
	
	
	

}
