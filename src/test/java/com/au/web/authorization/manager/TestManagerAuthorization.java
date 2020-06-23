package com.au.web.authorization.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.au.domain.Onboard;
import com.au.domain.OnboardLog;
import com.au.exception.customExceptions.UnauthorizedUserException;
import com.au.service.DemandService;
import com.au.service.OnboardLogService;
import com.au.service.OnboardService;
import com.au.web.authorization.AuthorizationLevel;
import com.au.web.authorization.LoginAuthorizationInterface;
import com.au.web.authorization.UserDataKey;
//import com.google.api.client.auth.openidconnect.IdToken;

@SpringBootTest
public class TestManagerAuthorization {
	

	@Mock
	OnboardService onboardServiceMock;
	
	@Mock
	OnboardLogService onboardLogServiceMock;
	
	@Mock
	DemandService demandServiceMock;
	
	
	@Mock
	LoginAuthorizationInterface userMock;
	

	@Spy
	ManagerAuthorization managerAuthorizationSpy;
	
	
	// data required for testing
	Map<UserDataKey , Object> userData ;
	String idToken = "testIdToken";
	List<Object> parameterList;
	
	Onboard onboard;
	
	
	@BeforeEach
	public void definingSpyBehaviour()
	{
		parameterList = new ArrayList<Object>();
//		parameterList.add(new Object());
//		parameterList.add(new Object());
		
		userData =  new EnumMap<UserDataKey, Object>(UserDataKey.class); 
		onboard = new Onboard();
		onboard.setOnb_id(10l);
		userData.put(UserDataKey.Email, "test_user_Email");
		userData.put(UserDataKey.AuthorizationLevel,AuthorizationLevel.manager);  
		
		when(managerAuthorizationSpy.getOnboardService()).thenReturn(onboardServiceMock);
		when(managerAuthorizationSpy.getOnboardLogService()).thenReturn(onboardLogServiceMock);
		when(managerAuthorizationSpy.getDemandService()).thenReturn(demandServiceMock);
		when(managerAuthorizationSpy.getUser()).thenReturn(userMock);
		when(userMock.getEmailAndAuthorizationLevel(idToken)).thenReturn(userData);
	}
	
	@AfterEach
	public void emptyingParameterList()
	{
		parameterList.clear();
	}
	
	
	@Test
	public void testGetAuthorizationNonParametarized()
	{
		
		
		//defining mock behaviour
//		Mockito.verify(user);
		when(userMock.getEmailAndAuthorizationLevel(anyString())).thenReturn(userData);
		
		
		
		//check
		assertEquals(AuthorizationLevel.manager, managerAuthorizationSpy.getAuthorization(idToken));
		
		
	}
	

	@Test
	public void testParameterizedGetAuthorizationLevelUnauthorisezUser()
	{
		
		//replacing authorization level to unauthorized
		userData.put(UserDataKey.AuthorizationLevel,AuthorizationLevel.unauthorizedUser);  
				
		when(onboardServiceMock.update(any(),eq((String)userData.get(UserDataKey.Email)))).thenReturn(onboard);
		
		Exception e = assertThrows(UnauthorizedUserException.class, ()->{managerAuthorizationSpy.getAuthorization(idToken, "OnboardService", "update", parameterList); });
		
	}	
	
	
	
	
	
	
	
// test onboard service switch cases	
	
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardUpdate()
	{
		
		parameterList.add(new Onboard());
		doReturn(onboardServiceMock).when(managerAuthorizationSpy).getOnboardService();
		when(onboardServiceMock.update(any(),eq((String)userData.get(UserDataKey.Email)))).thenReturn(onboard);
		
		assertEquals(onboard,(Onboard)managerAuthorizationSpy.getAuthorization(idToken,"OnboardService", "update", parameterList));
		
	}
	
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardDelete()
	{
		long onb_id = 1l;
		parameterList.add(onb_id);
		
		when(onboardServiceMock.delete(anyLong(),eq((String)userData.get(UserDataKey.Email)))).thenReturn(onboard);
		
		assertEquals(onboard,(Onboard)managerAuthorizationSpy.getAuthorization(idToken,"OnboardService", "delete", parameterList));
		
	}
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardGetAll()
	{
		List<Onboard> list = new ArrayList<Onboard>() ;
		
		doReturn(list).when(onboardServiceMock).getAll();
		
		assertEquals(list,(List<Onboard>)managerAuthorizationSpy.getAuthorization(idToken,"OnboardService", "getAll",null));
		
	}
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardGetByStartDate()
	{
		Date start_date = Date.valueOf("2020-02-02");
		
		parameterList.add(start_date);
		List<Onboard> list =new ArrayList<Onboard>() ;
		
		
		
		
		doReturn(list).when(onboardServiceMock).getByStartDate(any());
		
		assertEquals(list,(List<Onboard>)managerAuthorizationSpy.getAuthorization(idToken, "OnboardService", "getByStartDate",parameterList));
		
	}
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardGetByEtaOfCompletion()
	{
		List<Onboard> list =new ArrayList<Onboard> ();
		
		Date eta_of_completion = Date.valueOf("2020-02-02");
		parameterList.add(eta_of_completion);
		
		doReturn(list).when(onboardServiceMock).getByEtaOfCompletion(any());
		
		assertEquals(list,(List<Onboard>)managerAuthorizationSpy.getAuthorization(idToken,"OnboardService", "getByEtaOfCompletion",parameterList));
		
	}
	
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardGetByOnboardingStatus()
	{
		List<Onboard> list = new ArrayList<Onboard>() ;
		String onboarding_status =" test"; 
		parameterList.add(onboarding_status);
		
		
		
		doReturn(list).when(onboardServiceMock).getByOnboardingStatus(anyString());
		
		assertEquals(list,(List<Onboard>)managerAuthorizationSpy.getAuthorization(idToken,"OnboardService", "getByOnboardingStatus",parameterList));
		
	}
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardGetByBgcStatus()
	{
		List<Onboard> list = new ArrayList<Onboard> ();
		String bgc_status =" test"; 
		parameterList.add(bgc_status);
		
		doReturn(list).when(onboardServiceMock).getByBgcStatus(anyString());
		
		assertEquals(list,(List<Onboard>)managerAuthorizationSpy.getAuthorization(idToken,"OnboardService", "getByBgcStatus",parameterList));
		
	}
	
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardGetById()
	{
		Onboard onboard =  new Onboard();
		long onb_id = 1l;
		parameterList.add(onb_id);
		
		
		
		doReturn(onboard).when(onboardServiceMock).getById(anyLong());
		
		assertEquals(onboard,(Onboard)managerAuthorizationSpy.getAuthorization(idToken,"OnboardService", "getById",parameterList));
		
	}
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardGetByEmployeeIdAndDemandId()
	{
//		List<Onboard> list =new ArrayList<Onboard>() ;
		Onboard onboard = new Onboard();
		long emp_id = 1l;
		long dem_id = 1l;
		parameterList.add(emp_id);
		parameterList.add(dem_id);
		
		
		doReturn(onboard).when(onboardServiceMock).getByEmployeeIdAndDemandId(anyLong(), anyLong());
		
		assertEquals(onboard,(Onboard)managerAuthorizationSpy.getAuthorization(idToken,"OnboardService", "getByEmployeeIdAndDemandId",parameterList));
		
	}
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardAdd()
	{
		
		parameterList.add(onboard);
		
		doReturn(onboard).when(onboardServiceMock).add(eq(onboard),anyString());
		
		assertEquals(onboard,(Onboard)managerAuthorizationSpy.getAuthorization(idToken,"OnboardService", "add",parameterList));
		
	}
	
	
	
	
	
	//    test onboard log service switch cases
	
	
	
	
	
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardLogGetAllLog()
	{
		List<OnboardLog> list = new ArrayList<OnboardLog>();
		
		doReturn(list).when(onboardLogServiceMock).getAllLog();
		
		assertEquals(list,(List<OnboardLog>)managerAuthorizationSpy.getAuthorization(idToken,"OnboardLogService", "getAllLog",null));
		
	}
	

	@Test
	public void testParameterizedGetAuthorizationLevelOnboardLogGetAllLogByEmployeeId()
	{
		List<OnboardLog> list =new ArrayList<OnboardLog>() ;
		long emp_id = 0l;
		parameterList.add(emp_id);
		
		
		doReturn(list).when(onboardLogServiceMock).getAllLogByEmployeeId(anyLong());
		
		assertEquals(list,(List<OnboardLog>)managerAuthorizationSpy.getAuthorization(idToken,"OnboardLogService", "getAllLogByEmployeeId",parameterList));
		
	}
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardLogGetAllLogByDemandId()
	{
		List<OnboardLog> list =new ArrayList<OnboardLog>() ;
		long dem_id = 0l;
		parameterList.add(dem_id);
		
		
		doReturn(list).when(onboardLogServiceMock).getAllLogByDemandId(anyLong());
		
		assertEquals(list,(List<OnboardLog>)managerAuthorizationSpy.getAuthorization(idToken,"OnboardLogService", "getAllLogByDemandId",parameterList));
		
	}
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardLogGetAllLogByEmployeeAndDemandId()
	{
		List<OnboardLog> list =new ArrayList<OnboardLog>() ;
		long emp_id = 0l;
		long dem_id = 0l;
		parameterList.add(emp_id);
		parameterList.add(dem_id);
		
		doReturn(list).when(onboardLogServiceMock).getAllLogByEmployeeIdAndDemandId(anyLong(), anyLong());
		
		assertEquals(list,(List<OnboardLog>)managerAuthorizationSpy.getAuthorization(idToken,"OnboardLogService", "getAllLogByEmployeeIdAndDemandId",parameterList));
		
	}
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardLogGetAllLogByOperator()
	{
		List<OnboardLog> list =new ArrayList<OnboardLog>() ;
		String operator = "test_operator";
		parameterList.add(operator);
		
		
		doReturn(list).when(onboardLogServiceMock).getAllLogByOperator(anyString());
		
		assertEquals(list,(List<OnboardLog>)managerAuthorizationSpy.getAuthorization(idToken,"OnboardLogService", "getAllLogByOperator",parameterList));
		
	}
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardLogGetAllLogByOperation()
	{
		
		List<OnboardLog> list =new ArrayList<OnboardLog>() ;
		String operation = "test_operation";
		parameterList.add(operation);
		
		
		doReturn(list).when(onboardLogServiceMock).getAllLogByOperation(anyString());
		
		assertEquals(list,(List<OnboardLog>)managerAuthorizationSpy.getAuthorization(idToken,"OnboardLogService", "getAllLogByOperation",parameterList));
		
	}
	
	
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardLogGetAllLogBetweenTimestamp()
	{
		List<OnboardLog> list =new ArrayList<OnboardLog>() ;
		Date timestamp1 = Date.valueOf("2020-02-02");
		Date timestamp2 = Date.valueOf("2020-02-02");
		parameterList.add(timestamp1);
		parameterList.add(timestamp2);
		
		doReturn(list).when(onboardLogServiceMock).getAllLogBetweenTimestamp(any(),any());
		
		assertEquals(list,(List<OnboardLog>)managerAuthorizationSpy.getAuthorization(idToken,"OnboardLogService", "getAllLogBetweenTimestamp",parameterList));
		
	}
	
	

	@Test
	public void testParameterizedGetAuthorizationLevelOnboardLogGetAllLogByOnboardId()
	{
		List<OnboardLog> list =new ArrayList<OnboardLog>() ;
		long onb_id = 0l;
		
		parameterList.add(onb_id);
		
		doReturn(list).when(onboardLogServiceMock).getAllLogByOnboardId(anyLong());
		
		assertEquals(list,(List<OnboardLog>)managerAuthorizationSpy.getAuthorization(idToken,"OnboardLogService", "getAllLogByOnboardId",parameterList));
		
	}
	
	
	
	
	
	
	
	
	
//     test demand service switch cases
	
	
	

	@Test
	public void testParameterizedGetAuthorizationLevelDemandGetCountForAllLocation()
	{
		List<Map<String,Object>> list =new ArrayList<Map<String,Object>>() ;
		
		
		doReturn(list).when(demandServiceMock).getCountForAllLocation();
		
		assertEquals(list,(List<Map<String,Object>>)managerAuthorizationSpy.getAuthorization(idToken,"DemandService", "getCountForAllLocation",null));
		
	}
	
	
	
		
	

}
