package com.au.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.au.domain.Skill;
import com.au.domain.SkillMapper;

@Repository
public class SkillDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	SkillMapper skillMapper;
	
	public List<Skill> getAll(){
		
		String sql = "select * from skill";
		
		List<Skill> skillList = jdbcTemplate.query(sql,skillMapper);
		return skillList;
	}
	
	public Skill getById(long skill_id)
	{
		String sql = "select * from skill where skill_id = ?";
		Skill skill = jdbcTemplate.queryForObject(sql, new Object[] {skill_id}, skillMapper);
		
		return skill;
	}
	
	public Skill getByName(String skill_name)
	{
		String sql = "select * from skill where lower(skill_name) = ?";
		Skill skill = jdbcTemplate.queryForObject(sql, new Object[] {skill_name.toLowerCase()}, skillMapper);
		
		return skill;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
