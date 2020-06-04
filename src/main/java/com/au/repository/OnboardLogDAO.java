package com.au.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.au.domain.OnboardLog;
import com.au.domain.OnboardLogMapper;

@Component
public class OnboardLogDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	OnboardLogMapper onboardLogMapper;
	
	public int addLog( OnboardLog onboardLog  ) 
	{
		String sql = "insert into Onboard_Log (operator , operation , onb_id , emp_id , dem_id, start_date , eta_of_completion , onboarding_status , bgc_status) values(? , ? , ? ,? ,?, ? , ? , ? , ?)";
		Object[] parameter = new Object[] {onboardLog.getOperator().toLowerCase(),
											onboardLog.getOperation().name(),
											onboardLog.getOnb_id(),
											onboardLog.getEmp_id(),
											onboardLog.getDem_id(),
											onboardLog.getStart_date(),
											onboardLog.getEta_of_completion(),
											onboardLog.getOnboarding_status(),
											onboardLog.getBgc_status()};
		return jdbcTemplate.update(sql,parameter);
		
	
		

	}
	
	public List<OnboardLog> getAllLog()
	{
		
		String sql = "select * from Onboard_Log";
		List<OnboardLog> logList = jdbcTemplate.query(sql, onboardLogMapper );
		
		return logList;
		
	}
	
	
	
	
	
	

}
