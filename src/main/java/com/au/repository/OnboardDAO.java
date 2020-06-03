package com.au.repository;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.au.domain.Onboard;
import com.au.domain.OnboardMapper;

@Component
public class OnboardDAO {
//
	@Autowired
	OnboardMapper onboardMapper;

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public List<Onboard> getAll()
	{
		String sql = "select * from onboard";
		List<Onboard> onboardList = jdbcTemplate.query(sql,onboardMapper);
	    return onboardList;
	}

	public Onboard getById(long onb_id) {

		String sql = "select * from onboard where onb_id = ?";
		Onboard onboard = jdbcTemplate.queryForObject(sql, new Object[] { onb_id }, onboardMapper);

		return onboard;
	}

	public int add(Onboard onboard) {

		String sql = "insert into onboard (emp_id, dem_id, start_date, eta_of_completion, bgc_status ,onboarding_status)  values ( ? , ? , ? , ?,?,?)";

		Object[] parameters = new Object[] { onboard.getEmp_id(), onboard.getDem_id(), onboard.getStart_date(),
				onboard.getEta_of_completion(), onboard.getBgc_status(), onboard.getOnboarding_status() };

		return jdbcTemplate.update(sql, parameters);

	}

	public int update(Onboard onboard) {
		String sql = "update onboard set emp_id = ?,dem_id = ? , start_Date = ? , eta_of_completion = ? , onboarding_status = ? , bgc_status = ? where onb_id = ?";
		Object[] parameters = new Object[] { onboard.getEmp_id(), onboard.getDem_id(), onboard.getStart_date(),
				onboard.getEta_of_completion(), onboard.getOnboarding_status().toLowerCase(),
				onboard.getBgc_status().toLowerCase(), onboard.getOnb_id() };

		return jdbcTemplate.update(sql, parameters);
	}

	public int delete(long onb_id) {
		String sql = "delete from onboard where onb_id = ?";
		return jdbcTemplate.update(sql, new Object[] { onb_id });

	}

}
