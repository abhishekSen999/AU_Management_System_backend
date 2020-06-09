package com.au.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestDemandSkillset_DemandDAO {

	@Autowired
	DemandSkillset_DemandDAO demandSkillset_DemandDao;
	
	
	@Test
	public void testGetAllDemandWithSkillId()
	{
		
		assertEquals(2,demandSkillset_DemandDao.getAllDemandWithSkillId(1).size());
		
	}
	
}
