package com.au.web.authorization.manager;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.servlet.ModelAndView;

import com.au.domain.Onboard;
import com.au.domain.OnboardLog;
import com.au.service.DemandService;
import com.au.service.OnboardLogService;
import com.au.service.OnboardService;
import com.au.web.authorization.AuthorizationLevel;
import com.au.web.authorization.LoginAuthorization;
import com.au.web.authorization.LoginAuthorizationInterface;
import com.au.web.authorization.manager.ManagerAuthorization;
import com.au.web.authorization.manager.ManagerAuthorizationInterface;

@SpringBootTest
public class TestManagerAuthorization {
	
//	@Mock
//	LoginAuthorizationInterface user;
//	
	@Mock
	OnboardService onboardService;
	
	@Mock
	OnboardLogService onboardLogService;
	
	@Mock
	DemandService demandService;
//	
//	@Autowired
//	ManagerAuthorization managerAuthorization;
//	

//	
//	
	
	
	@Mock
	LoginAuthorizationInterface user;
	
//	@Autowired
	@Spy
	ManagerAuthorization managerAuthorization;
	
	@Test
	public void testGetAuthorizationNonParametarized()
	{
		
		//defining mock behaviour
//		Mockito.verify(user);
		doReturn(user).when(managerAuthorization).getUser();
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		
		
		
		//check
		assertEquals(AuthorizationLevel.manager, managerAuthorization.getAuthorization());
		
		
	}
	

	@Test
	public void testParameterizedGetAuthorizationLevelUnauthorisezUser()
	{
		
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(new Onboard());
		
		doReturn(user).when(managerAuthorization).getUser();
		doReturn(onboardService).when(managerAuthorization).getOnboardService();
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.unauthorizedUser);
		Mockito.when(onboardService.update((Onboard)parameterList.get(0))).thenReturn(0);
		
		assertTrue(managerAuthorization.getAuthorization("OnboardService", "update", parameterList) instanceof ModelAndView  );
		
	}	
	
	
	
	
	
	
	
// test onboard service switch cases	
	
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardUpdate()
	{
		
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(new Onboard());
		
		doReturn(user).when(managerAuthorization).getUser();
		doReturn(onboardService).when(managerAuthorization).getOnboardService();
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		Mockito.when(onboardService.update((Onboard)parameterList.get(0))).thenReturn(0);
		
		assertEquals(0,(int)managerAuthorization.getAuthorization("OnboardService", "update", parameterList));
		
	}
	
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardDelete()
	{
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(0L);
		doReturn(user).when(managerAuthorization).getUser();
		doReturn(onboardService).when(managerAuthorization).getOnboardService();
		
		
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		Mockito.when(onboardService.delete((long)parameterList.get(0))).thenReturn(0);
		
		assertEquals(0,(int)managerAuthorization.getAuthorization("OnboardService", "delete", parameterList));
		
	}
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardGetAll()
	{
		List<Onboard> list = new ArrayList<Onboard>() ;
		
		doReturn(user).when(managerAuthorization).getUser();
		doReturn(onboardService).when(managerAuthorization).getOnboardService();
		
		
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		doReturn(list).when(onboardService).getAll();
		
		assertEquals(list,(List<Onboard>)managerAuthorization.getAuthorization("OnboardService", "getAll",null));
		
	}
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardGetByStartDate()
	{
		List<Onboard> list =new ArrayList<Onboard>() ;
		Date start_date = Date.valueOf("2020-02-02");
		List<Object> parameterlist = new ArrayList<Object>();
		parameterlist.add(start_date);
		
		
		
		doReturn(user).when(managerAuthorization).getUser();
		doReturn(onboardService).when(managerAuthorization).getOnboardService();
		
		
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		doReturn(list).when(onboardService).getByStartDate(start_date);
		
		assertEquals(list,(List<Onboard>)managerAuthorization.getAuthorization("OnboardService", "getByStartDate",parameterlist));
		
	}
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardGetByEtaOfCompletion()
	{
		List<Onboard> list =new ArrayList<Onboard> ();
		Date eta_of_completion = Date.valueOf("2020-02-02");
		List<Object> parameterlist = new ArrayList<Object>();
		parameterlist.add(eta_of_completion);
		
		
		
		doReturn(user).when(managerAuthorization).getUser();
		doReturn(onboardService).when(managerAuthorization).getOnboardService();
		
		
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		doReturn(list).when(onboardService).getByEtaOfCompletion(eta_of_completion);
		
		assertEquals(list,(List<Onboard>)managerAuthorization.getAuthorization("OnboardService", "getByEtaOfCompletion",parameterlist));
		
	}
	
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardGetByOnboardingStatus()
	{
		List<Onboard> list = new ArrayList<Onboard>() ;
		String onboarding_status =" test"; 
		List<Object> parameterlist = new ArrayList<Object>();
		parameterlist.add(onboarding_status);
		
		
		
		doReturn(user).when(managerAuthorization).getUser();
		doReturn(onboardService).when(managerAuthorization).getOnboardService();
		
		
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		doReturn(list).when(onboardService).getByOnboardingStatus(onboarding_status);
		
		assertEquals(list,(List<Onboard>)managerAuthorization.getAuthorization("OnboardService", "getByOnboardingStatus",parameterlist));
		
	}
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardGetByBgcStatus()
	{
		List<Onboard> list = new ArrayList<Onboard> ();
		String bgc_status =" test"; 
		List<Object> parameterlist = new ArrayList<Object>();
		parameterlist.add(bgc_status);
		
		
		
		doReturn(user).when(managerAuthorization).getUser();
		doReturn(onboardService).when(managerAuthorization).getOnboardService();
		
		
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		doReturn(list).when(onboardService).getByBgcStatus(bgc_status);
		
		assertEquals(list,(List<Onboard>)managerAuthorization.getAuthorization("OnboardService", "getByBgcStatus",parameterlist));
		
	}
	
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardGetById()
	{
		Onboard onboard =  new Onboard();
		long onb_id = 1l;
		List<Object> parameterlist = new ArrayList<Object>();
		parameterlist.add(onb_id);
		
		
		
		doReturn(user).when(managerAuthorization).getUser();
		doReturn(onboardService).when(managerAuthorization).getOnboardService();
		
		
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		doReturn(onboard).when(onboardService).getById(onb_id);
		
		assertEquals(onboard,(Onboard)managerAuthorization.getAuthorization("OnboardService", "getById",parameterlist));
		
	}
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardGetByEmployeeIdAndDemandId()
	{
//		List<Onboard> list =new ArrayList<Onboard>() ;
		Onboard onboard = new Onboard();
		long emp_id = 1l;
		long dem_id = 1l;
		List<Object> parameterlist = new ArrayList<Object>();
		parameterlist.add(emp_id);
		parameterlist.add(dem_id);
		
		
		doReturn(user).when(managerAuthorization).getUser();
		doReturn(onboardService).when(managerAuthorization).getOnboardService();
		
		
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		doReturn(onboard).when(onboardService).getByEmployeeIdAndDemandId(emp_id, dem_id);
		
		assertEquals(onboard,(Onboard)managerAuthorization.getAuthorization("OnboardService", "getByEmployeeIdAndDemandId",parameterlist));
		
	}
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardAdd()
	{
		
		Onboard onboard = new Onboard();
		
		List<Object> parameterlist = new ArrayList<Object>();
		parameterlist.add(onboard);
	
		
		
		doReturn(user).when(managerAuthorization).getUser();
		doReturn(onboardService).when(managerAuthorization).getOnboardService();
		
		
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		doReturn(1).when(onboardService).add(onboard);
		
		assertEquals(1,(int)managerAuthorization.getAuthorization("OnboardService", "add",parameterlist));
		
	}
	
	
	
	
	
	//    test onboard log service switch cases
	
	
	
	
	
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardLogGetAllLog()
	{
		List<OnboardLog> list = new ArrayList<OnboardLog>();
		
		doReturn(user).when(managerAuthorization).getUser();
		doReturn(onboardLogService).when(managerAuthorization).getOnboardLogService();
		
		
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		doReturn(list).when(onboardLogService).getAllLog();
		
		assertEquals(list,(List<OnboardLog>)managerAuthorization.getAuthorization("OnboardLogService", "getAllLog",null));
		
	}
	

	@Test
	public void testParameterizedGetAuthorizationLevelOnboardLogGetAllLogByEmployeeId()
	{
		List<OnboardLog> list =new ArrayList<OnboardLog>() ;
		List<Object> parameterlist = new ArrayList<Object>();
		long emp_id = 0l;
		
		parameterlist.add(emp_id);
		
		doReturn(user).when(managerAuthorization).getUser();
		doReturn(onboardLogService).when(managerAuthorization).getOnboardLogService();
		
		
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		doReturn(list).when(onboardLogService).getAllLogByEmployeeId(emp_id);
		
		assertEquals(list,(List<OnboardLog>)managerAuthorization.getAuthorization("OnboardLogService", "getAllLogByEmployeeId",parameterlist));
		
	}
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardLogGetAllLogByDemandId()
	{
		List<OnboardLog> list =new ArrayList<OnboardLog>() ;
		List<Object> parameterlist = new ArrayList<Object>();
		long dem_id = 0l;
		
		parameterlist.add(dem_id);
		
		doReturn(user).when(managerAuthorization).getUser();
		doReturn(onboardLogService).when(managerAuthorization).getOnboardLogService();
		
		
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		doReturn(list).when(onboardLogService).getAllLogByDemandId(dem_id);
		
		assertEquals(list,(List<OnboardLog>)managerAuthorization.getAuthorization("OnboardLogService", "getAllLogByDemandId",parameterlist));
		
	}
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardLogGetAllLogByEmployeeAndDemandId()
	{
		List<OnboardLog> list =new ArrayList<OnboardLog>() ;
		List<Object> parameterlist = new ArrayList<Object>();
		long emp_id = 0l;
		long dem_id = 0l;
		parameterlist.add(emp_id);
		parameterlist.add(dem_id);
		
		doReturn(user).when(managerAuthorization).getUser();
		doReturn(onboardLogService).when(managerAuthorization).getOnboardLogService();
		
		
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		doReturn(list).when(onboardLogService).getAllLogByEmployeeIdAndDemandId(emp_id, dem_id);
		
		assertEquals(list,(List<OnboardLog>)managerAuthorization.getAuthorization("OnboardLogService", "getAllLogByEmployeeIdAndDemandId",parameterlist));
		
	}
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardLogGetAllLogByOperator()
	{
		List<OnboardLog> list =new ArrayList<OnboardLog>() ;
		List<Object> parameterlist = new ArrayList<Object>();
		String operator = "test_operator";
		parameterlist.add(operator);
		
		doReturn(user).when(managerAuthorization).getUser();
		doReturn(onboardLogService).when(managerAuthorization).getOnboardLogService();
		
		
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		doReturn(list).when(onboardLogService).getAllLogByOperator(operator);
		
		assertEquals(list,(List<OnboardLog>)managerAuthorization.getAuthorization("OnboardLogService", "getAllLogByOperator",parameterlist));
		
	}
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardLogGetAllLogByOperation()
	{
		List<OnboardLog> list =new ArrayList<OnboardLog>() ;
		List<Object> parameterlist = new ArrayList<Object>();
		String operation = "test_operation";
		parameterlist.add(operation);
		
		doReturn(user).when(managerAuthorization).getUser();
		doReturn(onboardLogService).when(managerAuthorization).getOnboardLogService();
		
		
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		doReturn(list).when(onboardLogService).getAllLogByOperation(operation);
		
		assertEquals(list,(List<OnboardLog>)managerAuthorization.getAuthorization("OnboardLogService", "getAllLogByOperation",parameterlist));
		
	}
	
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardLogGetAllLogBetweenTimestamp()
	{
		List<OnboardLog> list =new ArrayList<OnboardLog>() ;
		List<Object> parameterlist = new ArrayList<Object>();
		Date timestamp1 = Date.valueOf("2020-02-02");
		Date timestamp2 = Date.valueOf("2020-02-02");
		parameterlist.add(timestamp1);
		parameterlist.add(timestamp2);
		doReturn(user).when(managerAuthorization).getUser();
		doReturn(onboardLogService).when(managerAuthorization).getOnboardLogService();
		
		
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		doReturn(list).when(onboardLogService).getAllLogBetweenTimestamp(timestamp1, timestamp2);
		
		assertEquals(list,(List<OnboardLog>)managerAuthorization.getAuthorization("OnboardLogService", "getAllLogBetweenTimestamp",parameterlist));
		
	}
	
	

	@Test
	public void testParameterizedGetAuthorizationLevelOnboardLogGetAllLogByOnboardId()
	{
		List<OnboardLog> list =new ArrayList<OnboardLog>() ;
		List<Object> parameterlist = new ArrayList<Object>();
		long onb_id = 0l;
		
		parameterlist.add(onb_id);
		
		doReturn(user).when(managerAuthorization).getUser();
		doReturn(onboardLogService).when(managerAuthorization).getOnboardLogService();
		
		
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		doReturn(list).when(onboardLogService).getAllLogByOnboardId(onb_id);
		
		assertEquals(list,(List<OnboardLog>)managerAuthorization.getAuthorization("OnboardLogService", "getAllLogByOnboardId",parameterlist));
		
	}
	
	
	
	
	
	
	
	
	
//     test demand service switch cases
	
	
	

	@Test
	public void testParameterizedGetAuthorizationLevelDemandGetCountForAllLocation()
	{
		List<Map<String,Object>> list =new ArrayList<Map<String,Object>>() ;
		
		
		
		
		doReturn(user).when(managerAuthorization).getUser();
		doReturn(demandService).when(managerAuthorization).getDemandService();
		
		
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		doReturn(list).when(demandService).getCountForAllLocation();
		
		assertEquals(list,(List<Map<String,Object>>)managerAuthorization.getAuthorization("DemandService", "getCountForAllLocation",null));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
