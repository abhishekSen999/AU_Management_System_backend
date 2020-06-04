package com.au.repository;

import java.sql.Date;
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
	
	public List<OnboardLog> getAllLogByEmployeeId(long emp_id)
	{
		String sql = "select * from Onboard_Log where emp_id = ?";
		List<OnboardLog> logList = jdbcTemplate.query(sql,new Object[] {emp_id}, onboardLogMapper);
		return logList;
	}
	
	public List<OnboardLog> getAllLogByDemandId(long dem_id)
	{
		String sql = "select * from Onboard_Log where dem_id = ?";
		List<OnboardLog> logList = jdbcTemplate.query(sql,new Object[] {dem_id}, onboardLogMapper);
		return logList;
	}
	
	public List<OnboardLog> getAllLogByEmployeeIdAndDemandId(long emp_id , long dem_id)
	{
		String sql = "select * from Onboard_Log where emp_id = ? and dem_id = ?";
		List<OnboardLog> logList = jdbcTemplate.query(sql,new Object[] {emp_id , dem_id}, onboardLogMapper);
		return logList;
	}
	
	
	public List<OnboardLog> getAllLogByOperator(String operator)
	{
		String sql = "select * from Onboard_Log where operator = ?";
		List<OnboardLog> logList = jdbcTemplate.query(sql,new Object[] {operator}, onboardLogMapper);
		return logList;
	}
	
	public List<OnboardLog> getAllLogByOperation(String operation)
	{
		String sql = "select * from Onboard_Log where operation = ?";
		List<OnboardLog> logList = jdbcTemplate.query(sql,new Object[] {operation}, onboardLogMapper);
		return logList;
	}
	
	public List<OnboardLog> getAllLogBetweenTimestamp(Date timestamp1 ,Date timestamp2)
	{
		String sql = "select * from Onboard_Log where timestamp between ? and ?";
		List<OnboardLog> logList = jdbcTemplate.query(sql,new Object[] {timestamp1 , timestamp2}, onboardLogMapper);
		return logList;
	}
	
	
	
	
	
	
	
	
	
	

}
