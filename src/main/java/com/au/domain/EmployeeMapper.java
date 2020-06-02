package com.au.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper implements RowMapper<Employee>
{

	@Override
	public Employee mapRow(ResultSet row, int rowNum) throws SQLException {
		Employee employee = new Employee();
		
		employee.setName((String) row.getString("name"));
		employee.setEmp_id((long)row.getLong("emp_id"));
		employee.setCompany_email((String)row.getString("company_email"));
		employee.setPersonal_email((String)row.getString("personal_email"));
		employee.setLocation((String)row.getString("location"));
		
		
		return employee;
	}
	
}