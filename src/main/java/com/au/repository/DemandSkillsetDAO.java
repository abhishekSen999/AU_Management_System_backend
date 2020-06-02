package com.au.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.au.domain.Demand;
import com.au.domain.DemandMapper;
import com.au.domain.Skill;
import com.au.domain.SkillMapper;

@Component
public class DemandSkillsetDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	DemandMapper demandMapper;
	
	@Autowired
	SkillMapper skillMapper;
	
	public List<Demand> getAllDemandWithSkillId(long skill_id)
	{
		String sql = "select demand.dem_id,demand.creation_date,demand.experience_requirement,demand.h_id,demand.location,demand.number_people from demand join demand_skillset on demand.dem_id = demand_skillset.dem_id where demand_skillset.skill_id = ?";
		
		List<Demand> demandList = jdbcTemplate.query(sql,new Object[] {skill_id} ,demandMapper) ;
		return demandList;
	}
	
	
	public List<Skill> getAllSkillForDemandWithId(long dem_id)
	{
		
		String sql = "select skill.skill_id, skill.skill_name from skill join demand_skillset on demand_skillset.skill_id = skill.skill_id where demand_skillset.dem_id = ?";
		
		List<Skill> skillList = jdbcTemplate.query(sql ,new Object[] {dem_id}, skillMapper );
		
		return skillList;
		
	}
	
	

}
