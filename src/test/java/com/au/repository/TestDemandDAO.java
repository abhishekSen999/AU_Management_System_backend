package com.au.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestDemandDAO {

	@Autowired
	DemandDAO demandDao;
	
	@Test
	public void testGetAll()
	{
		assertEquals(4, demandDao.getAll().size());
	}
	
	@Test
	public void testGetById()
	{
		assertEquals(1,demandDao.getById(1).getDem_id());
	}
	
	@Test
	public void testGetAllDemandFromHiringManagerWithId()
	{
		assertEquals(2, demandDao.getAllDemandFromHiringManagerWithId(1).size());
	}
	
}
