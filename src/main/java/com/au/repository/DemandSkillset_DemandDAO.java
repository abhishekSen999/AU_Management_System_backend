package com.au.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.au.domain.Demand;
import com.au.domain.DemandMapper;

@Component
public class DemandSkillset_DemandDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	DemandMapper demandMapper;
	
	public List<Demand> getAllDemandWithSkillId(long skill_id)
	{
		String sql = "select demand.dem_id,demand.creation_date,demand.experience_requirement,demand.h_id,demand.location,demand.number_people from demand join demand_skillset on demand.dem_id = demand_skillset.dem_id where demand_skillset.skill_id = ?";
		
		List<Demand> demandList = jdbcTemplate.query(sql,new Object[] {skill_id} ,demandMapper) ;
		return demandList;
	}
	
	
}
