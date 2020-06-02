package com.au.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.au.domain.Employee;
import com.au.repository.EmployeeDAO;

@Component
public class EmployeeService {

	@Autowired
	EmployeeDAO employeeDao;

	public EmployeeService() {

	}

	public Collection<Employee> getAll() // returns all employees
	{
		return employeeDao.getAll();
	}

	public Employee getById(long emp_id) {
		

		return employeeDao.getById(emp_id);

		
	}

	public Employee getByCompanyEmail(String company_email) {
		

		return employeeDao.getByCompanyEmail(company_email.toLowerCase());

	}

	public Employee getByPersonalEmail(String personal_email) {
		

		return  employeeDao.getByPersonalEmail(personal_email.toLowerCase());

		
	}

	public Collection<Employee> getByLocation(String location) {
		
		return employeeDao.getByLocation(location);

		
	}

}
