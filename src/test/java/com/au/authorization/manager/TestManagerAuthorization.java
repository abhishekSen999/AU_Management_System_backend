package com.au.authorization.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.au.domain.Onboard;
import com.au.service.OnboardService;
import com.au.web.authorization.AuthorizationLevel;
import com.au.web.authorization.LoginAuthorizationInterface;
import com.au.web.authorization.manager.ManagerAuthorization;

@SpringBootTest
public class TestManagerAuthorization {
	
	@Mock
	LoginAuthorizationInterface user;
	
	@Mock
	OnboardService onboardService;
	
	@Autowired
	ManagerAuthorization managerAuthorization;
	
	@Test
	public void testParameterizedGetAuthorizationLevelOnboardDelete()
	{
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(0L);
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		Mockito.when(onboardService.delete((long)parameterList.get(0))).thenReturn(0);
		
		assertEquals(0,(int)managerAuthorization.getAuthorization("OnboardService", "delete", parameterList));
		
	}
	
	@Test
	public void testParameterizedGetAuthorizationLevelUpdate()
	{
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(new Onboard());
		Mockito.when(user.getAuthorizationLevel()).thenReturn(AuthorizationLevel.manager);
		Mockito.when(onboardService.update((Onboard)parameterList.get(0))).thenReturn(0);
		
		assertEquals(0,(int)managerAuthorization.getAuthorization("OnboardService", "update", parameterList));
		
	}

}
