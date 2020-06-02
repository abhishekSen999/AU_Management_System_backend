package com.au.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestDemandSkillsetDAO {

	@Autowired
	DemandSkillsetDAO demandSkillsetDao;
	
	
	@Test
	public void testGetAllDemandWithSkillId()
	{
		
		assertEquals(2,demandSkillsetDao.getAllDemandWithSkillId(1).size());
		
	}
	
	@Test
	public void tetGetAllSkillForDemandWithId()
	{
		assertEquals(3, demandSkillsetDao.getAllSkillForDemandWithId(1).size());
	}
	
	
	
}
