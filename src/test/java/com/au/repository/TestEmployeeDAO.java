package com.au.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TestEmployeeDAO {
	
	@Autowired
	EmployeeDAO employeeDao;
	
	@Test
	public void testGetUserByLocation() 
	{
		String location = "Mumbai";
		assertTrue(location.equalsIgnoreCase(employeeDao.getByLocation(location).get(0).getLocation()));
	}
	
	@Test
	public void testGetByName()
	{
		String name = "AbhisHek1 Sen";
		assertTrue(name.equalsIgnoreCase(employeeDao.getByName(name).get(0).getName()));
		
	}
	
	@Test
	public void testGetAll()
	{
		
		assertNotNull(employeeDao.getAll().get(0));
		
	}
	
	@Test 
	public void testGetById()
	{
		long emp_id = 1l;
		assertEquals(emp_id,employeeDao.getById(emp_id).getEmp_id());
	}
	@Test 
	public void testGetByCompanyEmail()
	{
		String company_email="Abhishek.sen@accoliteindia.com";
		assertTrue(company_email.equalsIgnoreCase(employeeDao.getByCompanyEmail(company_email).getCompany_email()));
	}
	
	@Test 
	public void testGetByPersonalEmail()
	{
		String personal_email="Abhishek1.sen@gmail.com";
		assertTrue(personal_email.equalsIgnoreCase(employeeDao.getByPersonalEmail(personal_email).getPersonal_email()));
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
