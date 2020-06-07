package com.au.domain;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestHiringManagerMapper {
	
	@Mock
	private ResultSet row;
	
	
	@Autowired
	HiringManagerMapper hiringManagerMapper;

	@Test
	public void testMapRow() throws SQLException
	{
		
		long h_id = 1;
		int country_code = 2;
		String dept = "test_dept";
		String email = "test_email";
		String name = "test_name";
		long ph_no = 3;
		String team_name = "test_team";
		
		
		
		//defining behavior of the mock
		Mockito.when(row.getLong("h_id")).thenReturn(h_id);
		Mockito.when(row.getInt("country_code")).thenReturn(country_code);
		Mockito.when(row.getString("dept")).thenReturn(dept);
		Mockito.when(row.getString("email")).thenReturn(email);
		Mockito.when(row.getString("name")).thenReturn(name);
		Mockito.when(row.getLong("ph_no")).thenReturn(ph_no);
		Mockito.when(row.getString("team_name")).thenReturn(team_name);
		
		
		HiringManager hiringManager = hiringManagerMapper.mapRow(row, 0);
		
		assertNotNull(hiringManager);
		assertEquals(h_id, hiringManager.getH_id());
		assertEquals(country_code, hiringManager.getCountry_code());
		assertEquals(dept, hiringManager.getDept());
		assertEquals(email, hiringManager.getEmail());
		assertEquals(name, hiringManager.getName());
		assertEquals(ph_no, hiringManager.getPh_no());
		assertEquals(team_name, hiringManager.getTeam_name());	
		
	}
	
}
