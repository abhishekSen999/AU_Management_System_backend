package com.au.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestSkillDAO {

	@Autowired
	SkillDAO skillDao;
	
	@Test
	public void testGetAll() {
		
		
		assertEquals(4, skillDao.getAll().size());
		
		
		
	}
@Test	
public void testGetById() {
		
		
		assertEquals(1, skillDao.getById(1).getSkill_id());
		
		
		
	}

@Test	
public void testGetByName() {
		
		
		assertEquals("java", skillDao.getByName("java").getSkill_name());
		
		
		
	}


	
	
	
	
}
