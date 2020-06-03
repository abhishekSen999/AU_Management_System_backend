package com.au.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestHiringManagerDAO {

	@Autowired
	HiringManagerDAO hiringManagerDao;
	
	
	@Test
	public void testGetAll()
	{
		
		assertEquals(2, hiringManagerDao.getAll().size());
		
	}
	
	@Test
	public void testGetById()
	{
		long h_id = 1;
		assertEquals(h_id, hiringManagerDao.getById(h_id).getH_id());
		
	}
	
	@Test
	public void testGetByName()
	{
		String name = "manager1";
		
		assertEquals(1, hiringManagerDao.getByName(name).size());
		assertNotNull(hiringManagerDao.getByName(name).get(0));
	}
	
	@Test
	public void testGetByEmail()
	{
		String email = "email1@gmail.com";
		
		assertEquals(email, hiringManagerDao.getByEmail(email).getEmail());
		
	}
	
	
	
}
