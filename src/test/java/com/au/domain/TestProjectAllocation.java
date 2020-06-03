package com.au.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestProjectAllocation {

	@Autowired
	ProjectAllocation projectAllocation;
	
	@Test
	public void testSetterGetterEmp_id()
	{
		long emp_id = 1;
		assertEquals(emp_id, projectAllocation.setEmp_id(emp_id).getEmp_id());
		
	}
	
	@Test
	public void testSetterGetterProj_id()
	{
		long proj_id = 1;
		assertEquals(proj_id, projectAllocation.setProj_id(proj_id).getProj_id());
		
	}
	
	@Test
	public void testSetterGetterStart_date()
	{
		Date start_date = Date.valueOf("2020-04-03");
		assertEquals(start_date, projectAllocation.setStart_date(start_date).getStart_date());
		
	}
	
	@Test
	public void testSetterGetterEnd_date()
	{
		Date end_date = Date.valueOf("2020-04-05");
		assertEquals(end_date, projectAllocation.setEnd_date(end_date).getEnd_date());
		
	}
	
	
	
	
	
	
}
