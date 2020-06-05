package com.au.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

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
		
//		DuplicateKeyException e;
		assertThrows(DuplicateKeyException.class,()->{onboardDao.add(onboard);});
		
		
	}
	
	@Test
	public void testUpdateOnboard()
	{
		Onboard onboard = new Onboard();
		onboard.setOnb_id(2);
		onboard.setBgc_status("started");
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
		
		long onb_id = 100;
		
		assertEquals(0,onboardDao.delete(onb_id)) ;
		
		
		
	}
	
	@Test
	public void testGetByEmployeeIdAndDemandId()
	{
		long emp_id = 1;
		long dem_id = 1;
		assertEquals(emp_id,onboardDao.getByEmployeeIdAndDemandId(emp_id, dem_id).getEmp_id());
		assertEquals(dem_id, onboardDao.getByEmployeeIdAndDemandId(emp_id, dem_id).getDem_id());
	}
	
	
	@Test
	public void testGetByStartDate()
	{
		Date start_date = Date.valueOf("2020-01-02");
		assertEquals(start_date, onboardDao.getByStartDate(start_date).get(0).getStart_date());
	}
	
	@Test
	public void testGetByEtaOfCompletion()
	{
		Date eta_of_completion = Date.valueOf("2020-02-02");
		assertEquals(eta_of_completion, onboardDao.getByEtaOfCompletion(eta_of_completion).get(0).getEta_of_completion());
	
	}
	@Test
	public void testGetByOnboardingStatus()
	{
		String onboarding_status = "s%";
		assertEquals('s', onboardDao.getByOnboardingStatus(onboarding_status).get(0).getOnboarding_status().charAt(0));
	
	}
	@Test
	public void testGetByBgcStatus()
	{
		String bgc_status = "s%";
		assertEquals('s', onboardDao.getByBgcStatus(bgc_status).get(0).getBgc_status().charAt(0));
		
	}
	
	@Test
	public void testGetNumberofOnboardForDemandId()
	{
		assertEquals(2,onboardDao.getNumberofOnboardForDemandId(2l));
	}
	
	
	
	
	
	
	
	
	
	
}
