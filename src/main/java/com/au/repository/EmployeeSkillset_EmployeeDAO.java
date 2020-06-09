package com.au.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.au.domain.Employee;
import com.au.domain.EmployeeMapper;

@Component
public class EmployeeSkillset_EmployeeDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	
	public List<Employee> getAllEmployeeWithSkillId(long skill_id)
	{
		String sql = "select employee.emp_id , employee.name , employee.company_email , employee.personal_email , employee.location from employee join employee_skillset on employee_skillset.emp_id = employee.emp_id where employee_skillset.skill_id =?";
		
		List<Employee> employeeList = jdbcTemplate.query(sql,new Object[]{skill_id}, employeeMapper);
		
		
		return employeeList;
		
	}
	
	
	
	
}
