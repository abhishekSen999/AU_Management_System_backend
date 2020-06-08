package com.au.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.au.domain.OnboardLog;
import com.au.repository.OnboardDAO;
import com.au.repository.OnboardLogDAO;

@SpringBootTest
public class TestOnboardLogService {
	
	
	@Spy
	OnboardLogService onboardLogServiceSpy;
	
	@Mock
	OnboardLogDAO onboardLogDao;
	
	
	
	@Test
	public void testGetAllLogByOnboardId()
	{
		long onb_id = 1l;
		List<OnboardLog> list = new ArrayList<OnboardLog>();
		
		doReturn(onboardLogDao).when(onboardLogServiceSpy).getOnboardLogDao();
		doReturn(list).when(onboardLogDao).getAllLogByOnboardId(onb_id);
		
		assertEquals(list, onboardLogServiceSpy.getAllLogByOnboardId(onb_id));
	}
	
	

}
