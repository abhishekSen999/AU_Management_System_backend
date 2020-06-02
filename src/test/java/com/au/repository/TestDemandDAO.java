package com.au.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	
	@Test
	public void testGetAllDemandBeforeDate() 
	{
		Date date = Date.valueOf("2020-04-05");
		assertEquals(1, demandDao.getAllDemandBeforeDate(date).size());
		
	}
	@Test
	public void testGetAllDemandAfterDate() 
	{
		Date date = Date.valueOf("2020-04-05");
		assertEquals(3, demandDao.getAllDemandAfterDate(date).size());
		
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
		
		assertEquals(3, demandDao.getAllDemandWithRequiredPeopleLessThan(6).size());
		
	}
	
	
	
	
}
