package com.au.repository;
import com.au.domain.*;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	public List<Employee> getAll(){
//		List<Employee> employeeList = new ArrayList<Employee>();
		
		String sql = "select * from employee";
		
		List<Employee> employeeList = jdbcTemplate.query(sql,employeeMapper);
		return employeeList;
		

	}
	
	
	
	public Employee getById(long emp_id)
	{
		String sql = "select * from employee where emp_id = ?";

		Employee employee = jdbcTemplate.queryForObject(sql, new Object[] {emp_id}, employeeMapper);
		
		return employee;
	}
	
	public Employee getByCompanyEmail(String company_email)
	{
		String sql = "select* from employee where lower(company_email) = ?";

		Employee employee = jdbcTemplate.queryForObject(sql, new Object[] {company_email.toLowerCase()}, employeeMapper);
		
		return employee;
	}
	
	public Employee getByPersonalEmail(String personal_email)
	{
		String sql = "select* from employee where lower(personal_email) = ?";

		Employee employee = jdbcTemplate.queryForObject(sql, new Object[] {personal_email.toLowerCase()}, employeeMapper);
		
		return employee;
	}
	
	public List<Employee> getByLocation(String location)
	{
		String sql = "select* from employee where lower(location) = ?";

		List<Employee> employeeList = jdbcTemplate.query(sql,new Object[]{location.toLowerCase()},employeeMapper);
		
		return employeeList;
	}
	
	public List<Employee> getByName(String name)
	{
		String sql = "select* from employee where lower(name) = ?";

		List<Employee> employeeList = jdbcTemplate.query(sql,new Object[]{name.toLowerCase()},employeeMapper);
		
		return employeeList;
	}
	
	
	
}
