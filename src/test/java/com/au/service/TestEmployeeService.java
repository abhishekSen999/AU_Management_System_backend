package com.au.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;


import com.au.domain.Employee;
import com.au.repository.EmployeeDAO;

@SpringBootTest
public class TestEmployeeService {

	@Spy
	EmployeeService employeeServiceSpy;
	
	@Mock
	EmployeeDAO employeeDao;
	
	@Test
	public void testGetAll() {
		List<Employee> list = new ArrayList<Employee>();
		doReturn(employeeDao).when(employeeServiceSpy).getEmployeeDao();
		Mockito.when(employeeDao.getAll()).thenReturn(list);
		
		assertEquals(list, employeeServiceSpy.getAll() );
	}
	
	@Test
	public void testGetById() {
		long emp_id =1l;
		Employee employee = new Employee();
		doReturn(employeeDao).when(employeeServiceSpy).getEmployeeDao();
		Mockito.when(employeeDao.getById(emp_id)).thenReturn(employee);
		
		assertEquals(employee, employeeServiceSpy.getById(emp_id) );
	}
	
	@Test
	public void testGetByCompanyEmail() {
		String company_email= "test";
		Employee employee = new Employee();
		doReturn(employeeDao).when(employeeServiceSpy).getEmployeeDao();
		Mockito.when(employeeDao.getByCompanyEmail(company_email)).thenReturn(employee);
		
		assertEquals(employee, employeeServiceSpy.getByCompanyEmail(company_email) );
	}
	
	@Test
	public void testGetByPersonalEmail() {
		String personal_email= "test";
		Employee employee = new Employee();
		doReturn(employeeDao).when(employeeServiceSpy).getEmployeeDao();
		Mockito.when(employeeDao.getByPersonalEmail(personal_email)).thenReturn(employee);
		
		assertEquals(employee, employeeServiceSpy.getByPersonalEmail(personal_email) );
	}
	
	@Test
	public void testGetByLocation() {
		String location= "test";
		List<Employee> list = new ArrayList<Employee>();
		doReturn(employeeDao).when(employeeServiceSpy).getEmployeeDao();
		Mockito.when(employeeDao.getByLocation(location)).thenReturn(list);
		
		assertEquals(list, employeeServiceSpy.getByLocation(location) );
	}
	
	
	
		
	
}
