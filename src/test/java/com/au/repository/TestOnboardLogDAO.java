package com.au.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.au.domain.OnboardLog;
import com.au.domain.Operation;

@SpringBootTest
public class TestOnboardLogDAO {
	
	@Autowired
	OnboardLogDAO onboardLogDAO;
	
	@Test
	public void testaddLog()
	{
		OnboardLog onboardLog = new OnboardLog();
		
		onboardLog.setOperator("test_operator1");
		onboardLog.setOperation(Operation.add);
		onboardLog.setOnb_id(1l);
		onboardLog.setEmp_id(1l);
		onboardLog.setDem_id(1l);
		onboardLog.setStart_date(Date.valueOf("2020-02-02"));
		onboardLog.setEta_of_completion(Date.valueOf("2020-02-02"));
		onboardLog.setOnboarding_status("started");
		onboardLog.setBgc_status("started");
		
		assertEquals(1, onboardLogDAO.addLog(onboardLog));
		
		
	}
	@Test
	public void testaddDeleteLog()
	{
		OnboardLog onboardLog = new OnboardLog();
		
		onboardLog.setOperator("test_operator2");
		onboardLog.setOperation(Operation.delete);
		onboardLog.setOnb_id(1l);
		
		assertEquals(1, onboardLogDAO.addLog(onboardLog));
		
		
	}
	
	
	@Test
	public void testGetAll()
	{
		OnboardLog obj = onboardLogDAO.getAllLog().get(0);
		System.out.println(">>>>>>>>>>>>"+obj);
		assertNotNull(obj);
	}
	

}
