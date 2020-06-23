package com.au.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.au.domain.Onboard;
import com.au.domain.OnboardMapper;
import com.au.domain.Operation;
import com.au.exception.customExceptions.InvalidDataEntryException;
import com.au.exception.customExceptions.RecordNotFoundException;
import com.au.service.OnboardLogService;

@SpringBootTest
public class TestOnboardDAO {
	
	@Spy
	OnboardDAO onboardDaoSpy;
	
	@Mock
	JdbcTemplate jdbcTemplateMock;
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	OnboardMapper onboardMapper;
	
	@Mock
	OnboardLogService onboardLogServiceMock;
	
	@BeforeEach
	public  void definingSpyBehaviour() {
		when(onboardDaoSpy.getJdbcTemplate()).thenReturn(jdbcTemplate);
		when(onboardDaoSpy.getOnboardMapper()).thenReturn(onboardMapper);
		when(onboardDaoSpy.getOnboardLogService()).thenReturn(onboardLogServiceMock);
//		when(onboardLogServiceMock.setLog(any(Operation.class)..matches(actual), any(Long.class) , any(String.class));
	}
	
	
	
	@Test
	public void testGetOnboardById()
	{
		long onb_id = 1;
		assertEquals(onb_id, onboardDaoSpy.getById(onb_id).getOnb_id());
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
		Exception exception = assertThrows(InvalidDataEntryException.class,()->{onboardDaoSpy.add(onboard,"test_operator");});
		assertEquals(" - Cannot Make this entry, EmployeeId: "+onboard.getEmp_id()+" and DemandId: "+onboard.getDem_id()+" entry is already present in the table - ", exception.getMessage());
		
	}
	
	@Test
	public void testUpdateOnboard()
	{
		String operator = "test_operator";
		Onboard onboard = new Onboard();
		onboard.setOnb_id(2);
		onboard.setBgc_status("started");
		onboard.setDem_id(1);
		onboard.setOnboarding_status("started");
		onboard.setEmp_id(5);
		onboard.setStart_date(Date.valueOf("2020-09-08"));
		onboard.setEta_of_completion(Date.valueOf("2020-09-07"));
		when(onboardLogServiceMock.setLog(Operation.update, onboard.getOnb_id(), operator)).thenReturn(1);
		
		assertTrue(onboard.equals(onboardDaoSpy.update(onboard,operator)));;
		
		
	}
	
	@Test
	public void testDeleteOnboard()
	{

		
		long onb_id = 100;
		
		Exception e = assertThrows(RecordNotFoundException.class,()->onboardDaoSpy.delete(onb_id,"test_operator")) ;
		
	}
	
	@Test  
	public void testGetByEmployeeIdAndDemandId()
	{
		long emp_id = 1;
		long dem_id = 1;
		assertEquals(emp_id,onboardDaoSpy.getByEmployeeIdAndDemandId(emp_id, dem_id).getEmp_id());
		assertEquals(dem_id, onboardDaoSpy.getByEmployeeIdAndDemandId(emp_id, dem_id).getDem_id());
	}
	
	
	@Test
	public void testGetByStartDate()
	{
		Date start_date = Date.valueOf("2020-01-02");
		assertEquals(start_date, onboardDaoSpy.getByStartDate(start_date).get(0).getStart_date());
	}
	
	@Test
	public void testGetByEtaOfCompletion()
	{
		Date eta_of_completion = Date.valueOf("2020-02-02");
		assertEquals(eta_of_completion, onboardDaoSpy.getByEtaOfCompletion(eta_of_completion).get(0).getEta_of_completion());
	
	}
	@Test
	public void testGetByOnboardingStatus()
	{
		String onboarding_status = "started";
		assertEquals('s', onboardDaoSpy.getByOnboardingStatus(onboarding_status).get(0).getOnboarding_status().charAt(0));
	
	}
	@Test
	public void testGetByOnboardingStatusWithWildcard()
	{
		String onboarding_status = "s%";
		assertEquals('s', onboardDaoSpy.getByOnboardingStatusWithWildcard(onboarding_status).get(0).getOnboarding_status().charAt(0));
	
	}
	
	
	@Test
	public void testGetByBgcStatus()
	{
		String bgc_status = "started";
		assertEquals('s', onboardDaoSpy. getByBgcStatus(bgc_status).get(0).getBgc_status().charAt(0));
		
	}
	
	@Test
	public void testGetByBgcStatusWithWildcard()
	{
		String bgc_status = "s%";
		assertEquals('s', onboardDaoSpy. getByBgcStatusWithWildcard(bgc_status).get(0).getBgc_status().charAt(0));
		
	}
	
	@Test
	public void testGetNumberofOnboardForDemandId()
	{
		assertEquals(0,onboardDaoSpy.getNumberofOnboardForDemandId(4));
	}
	
	@Test
	public void testGetAll()
	{
		assertNotNull(onboardDaoSpy.getAll().get(0));
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
