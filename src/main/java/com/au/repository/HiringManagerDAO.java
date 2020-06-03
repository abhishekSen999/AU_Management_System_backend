package com.au.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.au.domain.HiringManager;
import com.au.domain.HiringManagerMapper;

@Component
public class HiringManagerDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	HiringManagerMapper hiringManagerMapper;
	
	public List<HiringManager> getAll()
	{
		String sql = "select* from hiring_manager";
		List<HiringManager> hiringManagerList = jdbcTemplate.query(sql , hiringManagerMapper);		
		return hiringManagerList;
	}
	
	
	public  HiringManager getById(long h_id)
	{
		String sql = "select* from hiring_manager where h_id = ?";
		HiringManager hiringManager = jdbcTemplate.queryForObject(sql, new Object [] {h_id} , hiringManagerMapper);
		
		return hiringManager;
		
	}
	
	
	public List<HiringManager> getByName(String name)
	{
		String sql = "select * from hiring_manager where lower(name) = ?";
		List<HiringManager> hiringManagerList = jdbcTemplate.query(sql, new Object[] {name.toLowerCase()}, hiringManagerMapper );
		
		return hiringManagerList;
		
		
	}
	
	public HiringManager getByEmail(String email)
	{
		String sql = "select * from hiring_manager where lower(email) = ?";
		HiringManager hiringManager = jdbcTemplate.queryForObject(sql, new Object[] {email.toLowerCase()},hiringManagerMapper);
		return hiringManager;
		
	}
	
	
	
	
	
	// TODO: add get by ph no, team name dept
	
	
	
	
	
	
	
	
	

}
