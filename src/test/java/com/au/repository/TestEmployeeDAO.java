package com.au.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		assertEquals(2, employeeDao.getByLocation(location).size());
	}
	
	@Test
	public void testGetByName()
	{
		String name = "AbhisHek1 Sen";
		assertEquals(1,employeeDao.getByName(name).size());
		
	}
	
	
	

}
