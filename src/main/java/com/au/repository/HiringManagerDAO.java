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
		List<HiringManager> hiringManagerList = jdbcTemplate.query(sql,) 
		
		
	}

}
