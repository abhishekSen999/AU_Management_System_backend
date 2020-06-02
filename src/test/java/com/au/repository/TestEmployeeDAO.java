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
		String location = "mumbai";
		assertEquals(2, employeeDao.getByLocation(location).size());
	}
	

}
