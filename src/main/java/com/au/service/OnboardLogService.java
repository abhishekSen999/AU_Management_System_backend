package com.au.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.au.domain.Onboard;
import com.au.domain.OnboardLog;
import com.au.domain.Operation;
import com.au.repository.OnboardDAO;
import com.au.repository.OnboardLogDAO;
import com.au.web.security.OAuthAuthenticatedUserDataInterface;

@Component
public class OnboardLogService {
	
	@Autowired
	OnboardLogDAO onboardLogDao;
	
	@Autowired
	OAuthAuthenticatedUserDataInterface user;
	
	
	
	@Autowired
	OnboardService onboardService;
	
	public int setLog(Operation  operation , long onb_id )
	{
		
		
		OnboardLog onboardLog = new OnboardLog();
		String operator = user.getAuthenticatedUserEmail();
		onboardLog.setOnb_id(onb_id)
				.setOperation(operation)
				.setOperator(operator);
		
		
		if(operation == Operation.delete )
			return onboardLogDao.addLog(onboardLog);
		
		Onboard onboard =  onboardService.getById(onb_id);
		
		onboardLog.setEmp_id(onboard.getEmp_id())
				.setDem_id(onboard.getDem_id())
				.setStart_date(onboard.getStart_date())
				.setEta_of_completion(onboard.getEta_of_completion())
				.setOnboarding_status(onboard.getOnboarding_status())
				.setBgc_status(onboard.getBgc_status());
			
		
		return onboardLogDao.addLog(onboardLog);
		
		
	}
	
	
	public List<OnboardLog>getAllLog()
	{
		
		List<OnboardLog> logList = onboardLogDao.getAllLog();
		
		
		return logList;
	}
	

}
