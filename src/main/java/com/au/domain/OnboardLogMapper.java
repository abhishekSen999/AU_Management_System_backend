package com.au.domain;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class OnboardLogMapper implements RowMapper<OnboardLog>{

	@Override
	public OnboardLog mapRow(ResultSet row, int rowNum) throws SQLException {
		OnboardLog onboardLog = new OnboardLog();
		String operationString = row.getString("operation");
		Operation operation ;
		if(operationString.equalsIgnoreCase("add"))
			operation = Operation.add;
		else if(operationString.equalsIgnoreCase("update"))
			operation = Operation.update;
		else 
			operation = Operation.delete;
		
		
		onboardLog.setEmp_id((long)row.getLong("emp_id"))	
			.setDem_id((long)row.getLong("dem_id"))	
			.setOnb_id((long)row.getLong("onb_id"))
			.setStart_date((Date)row.getDate("start_date"))	
			.setEta_of_completion((Date)row.getDate("eta_of_completion"))
			.setBgc_status((String)row.getString("bgc_status"))
			.setOnboarding_status((String)row.getString("onboarding_status"))
			.setLog_id((long)row.getLong("log_id"))
			.setTimestamp((Timestamp)row.getTimestamp("timestamp"))
			.setOperator((String) row.getString("operator"))
			.setOperation(operation); 
		return onboardLog;
	}

}
