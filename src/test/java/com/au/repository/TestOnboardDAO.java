package com.au.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.au.domain.Onboard;

@SpringBootTest
public class TestOnboardDAO {
	
	@Autowired
	OnboardDAO onboardDao;
	
	@Test
	public void testGetOnboardById()
	{
		long onb_id = 1;
		assertEquals(onb_id, onboardDao.getById(onb_id).getOnb_id());
	}
	
	@Test
	public void testAddOnboard()
	{
		Onboard onboard = new Onboard();
		onboard.setBgc_status("starting");
		onboard.setDem_id(1);
		onboard.setOnboarding_status("started");
		onboard.setEmp_id(5);
		onboard.setStart_date(Date.valueOf("2020-09-08"));
		onboard.setEta_of_completion(Date.valueOf("2020-09-07"));
		
		assertEquals(1,onboardDao.add(onboard));
		
		
	}
	
	@Test
	public void testUpdateOnboard()
	{
		Onboard onboard = new Onboard();
		onboard.setOnb_id(2);
		onboard.setBgc_status("starting");
		onboard.setDem_id(1);
		onboard.setOnboarding_status("started");
		onboard.setEmp_id(5);
		onboard.setStart_date(Date.valueOf("2020-09-08"));
		onboard.setEta_of_completion(Date.valueOf("2020-09-07"));
		
		assertEquals(1,onboardDao.update(onboard));
		
		
	}
	
	@Test
	public void testDeleteOnboard()
	{
//		Onboard onboard = new Onboard();
//		onboard.setOnb_id(2);
//		onboard.setBgc_status("starting");
//		onboard.setDem_id(1);
//		onboard.setOnboarding_status("started");
//		onboard.setEmp_id(5);
//		onboard.setStart_date(Date.valueOf("2020-09-08"));
//		onboard.setEta_of_completion(Date.valueOf("2020-09-07"));
		
		long onb_id = 2;
		assertEquals(1,onboardDao.delete(onb_id));
		
		
	}
	
	
	
	
	
	
}
