package com.au.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestEmployeeSkillSet_SkillDAO {

	@Autowired
	EmployeeSkillset_SkillDAO employeeSkillset_SkillDao;
	
	@Test
	public void testGetAllSkillOfEmployeeWithId()
	{
		
		assertEquals(4,employeeSkillset_SkillDao.getAllSkillOfEmployeeWithId(1).size() );
		
	}
	
	
//
	
}
