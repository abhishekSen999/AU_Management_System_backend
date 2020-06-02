package com.au.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestEmployeeSkillSetDAO {

	@Autowired
	EmployeeSkillsetDAO employeeSkillsetDAO;
	
	@Test
	public void testGetAllSkillOfEmployeeWithId()
	{
		
		assertEquals(4,employeeSkillsetDAO.getAllSkillOfEmployeeWithId(1).size() );
		
	}
	
	
	@Test
	public void testGetAllEmployeeWithSkillId()
	{
		assertEquals(4, employeeSkillsetDAO.getAllEmployeeWithSkillId(1).size());
	}
	
}
