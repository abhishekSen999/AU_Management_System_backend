package com.au.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.RowMapper;

@SpringBootTest
public class TestProjectAllocationMapper {

	@Mock
	ResultSet row;
	
	@Autowired
	ProjectAllocationMapper projectAllocationMapper;
	
	
	@Test
	public void testMapRow() throws SQLException
	{
		long emp_id = 1;
		long proj_id = 2;
		Date start_date = Date.valueOf("2020-01-01");
		Date end_date = Date.valueOf("2020-05-1");
		
		//defining behaviour
		Mockito.when(row.getLong("emp_id")).thenReturn(emp_id);
		Mockito.when(row.getLong("proj_id")).thenReturn(proj_id);
		Mockito.when(row.getDate("start_date")).thenReturn(start_date);
		Mockito.when(row.getDate("end_date")).thenReturn(end_date);
		
		
		ProjectAllocation projectAllocation = projectAllocationMapper.mapRow(row,0);
		
		//testing
		assertNotNull(projectAllocation);
		assertEquals(emp_id, projectAllocation.getEmp_id());
		assertEquals(proj_id, projectAllocation.getProj_id());
		assertEquals(start_date, projectAllocation.getStart_date());
		assertEquals(end_date, projectAllocation.getEnd_date());
		
		
		
		
		
	}
	
	
	
}
