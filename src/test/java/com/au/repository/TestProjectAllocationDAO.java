package com.au.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestProjectAllocationDAO {

	@Autowired
	ProjectAllocationDAO projectAllocationDAO;
	
	@Test
	public void testGetAllProjectAllocationOfEmployeeWithId()
	{
		assertEquals(4,projectAllocationDAO.getAllProjectAllocationOfEmployeeWithId(1).size());
	}
	
	@Test
	public void testGetAllProjectAllocation() {
		
		assertEquals(7, projectAllocationDAO.getAllProjectAllocation().size());
		
	}
	
	@Test
	public void testGetLastProjectAllocationOfEmployeeWithId()
	{
		Date last_end_date = Date.valueOf("2020-07-30");
		long emp_id = 1;
		assertEquals(last_end_date,projectAllocationDAO.getLastProjectAllocationOfEmployeeWithId(emp_id).getEnd_date() );
		
	}
	
	@Test
	public void testGetAllEmployeeWhoAreFreeByDate()
	{
		Date date = Date.valueOf("2020-04-01");
		assertEquals(2,projectAllocationDAO.getAllEmployeeWhoAreFreeByDate(date).size());
		
	}
	@Test
	public void testGetAllEmployeeWhoAreFreeAfterDate()
	{
		Date date = Date.valueOf("2020-03-01");
		assertEquals(1,projectAllocationDAO.getAllEmployeeWhoAreFreeByDate(date).size());
		
	}
	
	
	
	
	
	
	
	
}
