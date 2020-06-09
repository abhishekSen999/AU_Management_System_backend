package com.au.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestEmployeeSkillset_EmployeeDAO {

	@Autowired
	EmployeeSkillset_EmployeeDAO employeeSkillset_EmployeeDao;
	
	
	@Test
	public void testGetAllEmployeeWithSkillId()
	{
		assertEquals(4, employeeSkillset_EmployeeDao.getAllEmployeeWithSkillId(1).size());
	}
	
}
