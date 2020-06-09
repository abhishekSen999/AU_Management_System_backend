package com.au.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import com.au.domain.DemandMapper;
import com.au.domain.Skill;
import com.au.domain.SkillMapper;

@Component
public class DemandSkillset_SkillDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	DemandMapper demandMapper;
	
	@Autowired
	SkillMapper skillMapper;
	

	
	
	public List<Skill> getAllSkillForDemandWithId(long dem_id)
	{
		
		String sql = "select skill.skill_id, skill.skill_name from skill join demand_skillset on demand_skillset.skill_id = skill.skill_id where demand_skillset.dem_id = ?";
		
		List<Skill> skillList = jdbcTemplate.query(sql ,new Object[] {dem_id}, skillMapper );
		
		return skillList;
		
	}
	
	

}
