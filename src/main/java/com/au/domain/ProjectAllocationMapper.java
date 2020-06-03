package com.au.domain;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ProjectAllocationMapper implements RowMapper<ProjectAllocation>{

	@Override
	public ProjectAllocation mapRow(ResultSet row, int rowNum) throws SQLException {
		ProjectAllocation projectAllocation = new ProjectAllocation();
		
		projectAllocation.setEmp_id((long)row.getLong("emp_id"))
			.setProj_id((long)row.getLong("proj_id"))
			.setStart_date((Date)row.getDate("start_date"))
			.setEnd_date((Date)row.getDate("end_date"));
		
		return projectAllocation; 
		
		
	}

	
	
}
