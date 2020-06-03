package com.au.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.au.domain.Employee;
import com.au.domain.EmployeeMapper;
import com.au.domain.ProjectAllocation;
import com.au.domain.ProjectAllocationMapper;

@Component
public class ProjectAllocationDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	ProjectAllocationMapper projectAllocationMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	public List<ProjectAllocation> getAllProjectAllocation()
	{
		
		String sql = "select * from project_allocation";
		List<ProjectAllocation> projectAllocationList = jdbcTemplate.query(sql, projectAllocationMapper);
		
		return projectAllocationList;
		
		
	}
	
	public List<ProjectAllocation> getAllProjectAllocationOfEmployeeWithId(long emp_id)
	{
		
		String sql = "select * from project_allocation where emp_id = ?";
		List<ProjectAllocation> projectAllocationList = jdbcTemplate.query(sql,new Object[] {emp_id} , projectAllocationMapper);
		
		return projectAllocationList;
		
	}
	
	
	
	public ProjectAllocation getLastProjectAllocationOfEmployeeWithId(long emp_id)
	{
		
		String sql = "select project_allocation.emp_id, project_allocation.proj_id, project_allocation.start_date, project_allocation.end_date "
				+" from project_allocation "
				+" join(select emp_id , max(end_date)  end_date "
					+"  from project_allocation "
					+"group by emp_id) as last_project "
				+" on project_allocation.end_date = last_project.end_date "
				+" where project_allocation.emp_id = ? ";
		
		
		ProjectAllocation projectAllocation = jdbcTemplate.queryForObject(sql,new Object[] {emp_id} , projectAllocationMapper);
		
		return projectAllocation;
		
	}
	
	
	public List<Employee> getAllEmployeeWhoAreFreeByDate(Date date){
	
		String sql = "select employee.emp_id,employee.company_email,employee.location, employee.name , employee.personal_email"
				+" from employee"
				+" join (select emp_id , max(end_date)  end_date"
				+"	 from project_allocation"
				+"	 group by emp_id) as last_project"
				+" on employee.emp_id = last_project.emp_id"
				+" where last_project.end_date < ?"; 
		
		List<Employee> employeeList = jdbcTemplate.query(sql,new Object[] {date}, employeeMapper );
		
		return employeeList; 
	}
	
	
	
	
	
	
	
	

}
