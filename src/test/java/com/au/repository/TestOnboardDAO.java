package com.au.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

import com.au.customExceptions.InvalidDataEntryException;
import com.au.domain.Onboard;

@SpringBootTest
public class TestOnboardDAO {
	
	@Spy
	OnboardDAO onboardDao;
	
	@Test
	public void testGetOnboardById()
	{
		long onb_id = 1;
		assertEquals(onb_id, onboardDao.getById(onb_id).getOnb_id());
	}
	
	@Test
	public void testAddOnboardInvalidDataEntryException()
	{
		Onboard onboard = new Onboard();
		onboard.setBgc_status("starting");
		onboard.setDem_id(1);
		onboard.setOnboarding_status("started");
		onboard.setEmp_id(5);
		onboard.setStart_date(Date.valueOf("2020-09-08"));
		onboard.setEta_of_completion(Date.valueOf("2020-09-07"));
		
//		DuplicateKeyException e;
		Exception exception = assertThrows(InvalidDataEntryException.class,()->{onboardDao.add(onboard);});
		assertEquals(" - Cannot Make this entry, EmployeeId: "+onboard.getEmp_id()+" and DemandId: "+onboard.getDem_id()+" entry is already present in the table - ", exception.getMessage());
		
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
		
//		assertThrows(DuplicateKeyException.class,()->{onboardDao.update(onboard);});
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
		String onboarding_status = "started";
		assertEquals('s', onboardDao.getByOnboardingStatus(onboarding_status).get(0).getOnboarding_status().charAt(0));
	
	}
	@Test
	public void testGetByOnboardingStatusWithWildcard()
	{
		String onboarding_status = "s%";
		assertEquals('s', onboardDao.getByOnboardingStatusWithWildcard(onboarding_status).get(0).getOnboarding_status().charAt(0));
	
	}
	
	
	@Test
	public void testGetByBgcStatus()
	{
		String bgc_status = "started";
		assertEquals('s', onboardDao. getByBgcStatus(bgc_status).get(0).getBgc_status().charAt(0));
		
	}
	
	@Test
	public void testGetByBgcStatusWithWildcard()
	{
		String bgc_status = "s%";
		assertEquals('s', onboardDao. getByBgcStatusWithWildcard(bgc_status).get(0).getBgc_status().charAt(0));
		
	}
	
	@Test
	public void testGetNumberofOnboardForDemandId()
	{
		assertEquals(0,onboardDao.getNumberofOnboardForDemandId(2l));
	}
	
	@Test
	public void testGetAll()
	{
		assertNotNull(onboardDao.getAll().get(0));
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
