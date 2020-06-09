package com.au.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestDemandSkillset_SkillDAO {

	@Autowired
	DemandSkillset_SkillDAO demandSkillset_SkillDao;
	
	

	
	@Test
	public void tetGetAllSkillForDemandWithId()
	{
		assertEquals(3, demandSkillset_SkillDao.getAllSkillForDemandWithId(1).size());
	}
	
	
	
}
