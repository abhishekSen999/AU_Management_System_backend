package com.au.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.sql.Date;
import java.util.List;

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
	
	@Test
	public void testGetAllLogByEmployeeId()
	{
		long emp_id = 1l;
		List<OnboardLog> logList = onboardLogDAO.getAllLogByEmployeeId(emp_id);
		assertEquals(emp_id, logList.get(0).getEmp_id());
	}
	
	@Test
	public void testGetAllLogByDemandId()
	{
		long dem_id = 1l;
		List<OnboardLog> logList = onboardLogDAO.getAllLogByDemandId(dem_id);
		assertEquals(dem_id, logList.get(0).getDem_id());
	}
	
	@Test
	public void testGetAllLogByEmployeeAndDemandId()
	{
		long dem_id = 1l;
		long emp_id = 1l;
		List<OnboardLog> logList = onboardLogDAO.getAllLogByEmployeeIdAndDemandId(emp_id, dem_id);
		assertEquals(dem_id, logList.get(0).getDem_id());
		assertEquals(emp_id, logList.get(0).getEmp_id());
	}
	

	@Test
	public void testGetAllLogByOperator()
	{
		String operator = "Abhishek.sen999@gmail.com";
		List<OnboardLog> logList = onboardLogDAO.getAllLogByOperator(operator);
		assertTrue(operator.equalsIgnoreCase(logList.get(0).getOperator()));
	}
	
	
	@Test
	public void testGetAllLogByOperation()
	{ 
		Operation operation = Operation.add;
		List<OnboardLog> logList = onboardLogDAO.getAllLogByOperation(operation.name());
		assertEquals(operation, logList.get(0).getOperation());
	}
	
	@Test
	public void testGetAllLogBetweenTimestamp()
	{
		Date timestamp1 = Date.valueOf("1990-01-01");
		Date timestamp2 = Date.valueOf("2022-01-01");
		List<OnboardLog> logList = onboardLogDAO.getAllLogBetweenTimestamp(timestamp1, timestamp2);
		assertNotNull(logList);
		
	}
	
	@Test
	public void testGetAllLogByOnboardId()
	{
		long onb_id = 1l;
		List<OnboardLog> logList = onboardLogDAO.getAllLogByOnboardId(onb_id);
		assertEquals(onb_id, logList.get(0).getOnb_id());		
	}
	
	
	
	
	
	
	
	
	
	

}
