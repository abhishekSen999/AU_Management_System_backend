package com.au.repository;
import com.au.domain.*;


import java.util.Collection;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	public Collection<Employee> getAll(){
//		List<Employee> employeeList = new ArrayList<Employee>();
		
		String sql = "select * from employee";
		
		Collection<Employee> employeeCollection = jdbcTemplate.query(sql,employeeMapper);
		return employeeCollection;
		

	}
	
	
	
	public Employee getById(long emp_id)
	{
		String sql = "select * from employee where emp_id = ?";

		Employee employee = jdbcTemplate.queryForObject(sql, new Object[] {emp_id}, employeeMapper);
		
		return employee;
	}
	
	public Employee getByCompanyEmail(String company_email)
	{
		String sql = "select* from employee where company_email = ?";

		Employee employee = jdbcTemplate.queryForObject(sql, new Object[] {company_email}, employeeMapper);
		
		return employee;
	}
	
	public Employee getByPersonalEmail(String personal_email)
	{
		String sql = "select* from employee where personal_email = ?";

		Employee employee = jdbcTemplate.queryForObject(sql, new Object[] {personal_email}, employeeMapper);
		
		return employee;
	}
	
	public Collection<Employee> getByLocation(String location)
	{
		String sql = "select* from employee where location = ?";

		Collection<Employee> employeeCollection = jdbcTemplate.query(sql,new Object[]{location},employeeMapper);
		
		return employeeCollection;
	}
	
	
}
