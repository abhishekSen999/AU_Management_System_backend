package com.au.domain;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


@Component
public class DemandMapper implements RowMapper<Demand> {

	@Override
	public Demand mapRow(ResultSet row, int rowNum) throws SQLException {

		Demand demand = new Demand();

		demand.setDem_id((long) row.getLong("dem_id"))
			.setCreation_date((Date) row.getDate("creation_date"))
			.setExperience_requirement((int) row.getInt("experience_requirement"))
			.setLoction((String) row.getString("location"))
			.setH_id((long) row.getLong("h_id"))
			.setNumber_people((int) row.getInt("number_people"));

		return demand;
	}

}
