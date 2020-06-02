package com.au.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class HiringManagerMapper implements RowMapper<HiringManager> {

	@Override
	public HiringManager mapRow(ResultSet row, int rowNum) throws SQLException {
		HiringManager hiringManager = new HiringManager();
		
		hiringManager.setH_id((long)row.getLong("h_id"))
			.setCountry_code((int)row.getInt("country_code"))
			.setDept((String)row.getString("dept"))
			.setEmail((String)row.getString("email"))
			.setName((String)row.getString("name"))
			.setPh_no((long)row.getLong("ph_no"))
			.setTeam_name((String)row.getString("team_name"));
		
		return hiringManager;
	
	}
	
	
	
}
