package com.au.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.au.domain.Employee;
import com.au.repository.EmployeeDAO;

@Component
public class EmployeeService {

	@Autowired
	EmployeeDAO employeeDao;
	
	EmployeeDAO getEmployeeDao()
	{
		return employeeDao;
	}
	
	
	
	public List<Employee> getAll() // returns all employees
	{
		return getEmployeeDao().getAll();
	}

	public Employee getById(long emp_id) {
		

		return getEmployeeDao().getById(emp_id); 

		
	}

	public Employee getByCompanyEmail(String company_email) {
		

		return getEmployeeDao().getByCompanyEmail(company_email.toLowerCase());

	}

	public Employee getByPersonalEmail(String personal_email) {
		

		return  getEmployeeDao().getByPersonalEmail(personal_email.toLowerCase());

		
	}

	public List<Employee> getByLocation(String location) {
		
		return getEmployeeDao().getByLocation(location);

		
	}

}
