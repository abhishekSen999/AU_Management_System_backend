package com.au.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestHiringManager {
	
	@Autowired
	HiringManager hiringManager;

	@Test
	public void testSetterGetterH_id()
	{
		long h_id=1;
		assertEquals(h_id, hiringManager.setH_id(h_id).getH_id()) ;
	}
	@Test
	public void testSetterGetterEmail()
	{
		String email = "test_email";
		assertEquals(email, hiringManager.setEmail(email).getEmail()) ;
	}
	@Test
	public void testSetterGetterPh_no()
	{
		long ph_no = 1111; // test ph no
		assertEquals(ph_no, hiringManager.setPh_no(ph_no).getPh_no());
	}
	@Test
	public void testSetterGetterCountry_code()
	{
		int country_code = 1;//test country code
		
		assertEquals(country_code, hiringManager.setCountry_code(country_code).getCountry_code()) ;
	}
	@Test
	public void testSetterGetterTeam_name()
	{
		String team_name = "test_team";
		
		assertEquals(team_name,hiringManager.setTeam_name(team_name).getTeam_name()) ;
	}
	@Test
	public void testSetterGetterDept()
	{
		String dept = "test_dept";
		
		assertEquals(dept, hiringManager.setDept(dept).getDept()); ;
	}
	
	
	
	
	
	
}
