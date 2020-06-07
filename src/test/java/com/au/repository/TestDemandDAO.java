package com.au.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

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
		assertEquals(9, demandDao.getAll().size());
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
	
	@Test
	public void testGetAllDemandBeforeDate() 
	{
		Date date = Date.valueOf("2020-04-05");
		assertEquals(5, demandDao.getAllDemandBeforeDate(date).size());
		
	}
	@Test
	public void testGetAllDemandAfterDate() 
	{
		Date date = Date.valueOf("2020-04-05");
		assertEquals(4, demandDao.getAllDemandAfterDate(date).size());
		
	}
	
	@Test
	public void testGetAllDemandForLocation() 
	{
		
		assertEquals(4, demandDao.getAllDemandForLocation("Mumbai").size());
		
	}
	
	@Test
	public void testGetAllDemandWithRequiredPeopleMoreThan() 
	{
		
		assertEquals(1, demandDao.getAllDemandWithRequiredPeopleMoreThan(5).size());
		
	}
	
	@Test
	public void testGetAllDemandWithRequiredPeopleLessThan() 
	{
		
		assertEquals(8, demandDao.getAllDemandWithRequiredPeopleLessThan(6).size());
		
	}
	
	@Test
	public void testGetCountForAllLocation()
	{
		System.out.println(demandDao.getCountForAllLocation());
		assertNotNull(demandDao.getCountForAllLocation());
	}
	
	
	
	
}
