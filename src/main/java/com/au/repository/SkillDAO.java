package com.au.repository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.au.domain.Employee;
import com.au.domain.Skill;
import com.au.domain.SkillMapper;

@Repository
public class SkillDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	SkillMapper skillMapper;
	
	public Collection<Skill> getAll(){
		
		String sql = "select * from skill";
		
		Collection<Skill> skillCollection = jdbcTemplate.query(sql,skillMapper);
		return skillCollection;
	}
	
	public Skill getById(long skill_id)
	{
		String sql = "select * from skill where skill_id = ?";
		Skill skill = jdbcTemplate.queryForObject(sql, new Object[] {skill_id}, skillMapper);
		
		return skill;
	}
	
	public Skill getByName(String skill_name)
	{
		String sql = "select * from skill where skill_name = ?";
		Skill skill = jdbcTemplate.queryForObject(sql, new Object[] {skill_name}, skillMapper);
		
		return skill;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
