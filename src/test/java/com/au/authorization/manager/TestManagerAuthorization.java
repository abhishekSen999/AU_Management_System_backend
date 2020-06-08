package com.au.authorization.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.au.domain.Onboard;
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
	public void testParameterizedGetAuthorizationLevelUpdate()
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
		List<Onboard> list =null ;
		
		doReturn(user).when(managerAuthorization).getUser();
		doReturn(onboardService).when(managerAuthorization).getOnboardService();
		
		
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		doReturn(list).when(onboardService).getAll();
		
		assertEquals(list,(List<Onboard>)managerAuthorization.getAuthorization("OnboardService", "getAll",null));
		
	}
	
	
	
	

}
