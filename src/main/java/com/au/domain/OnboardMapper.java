package com.au.domain;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


@Component
public class OnboardMapper implements RowMapper<Onboard> {

	@Override
	public Onboard mapRow(ResultSet row, int rowNum) throws SQLException {
		Onboard onboard = new Onboard();
		
		onboard.setEmp_id((long)row.getLong("emp_id"));
		onboard.setDem_id((long)row.getLong("dem_id"));
		onboard.setOnb_id((long)row.getLong("onb_id"));
		onboard.setStart_date((Date)row.getDate("start_date"));
		onboard.setEta_of_completion((Date)row.getDate("eta_of_completion"));
		onboard.setBgc_status((String)row.getString("bgc_status"));
		onboard.setOnboarding_status((String)row.getString("onboarding_status"));
		return onboard;
	}
}
