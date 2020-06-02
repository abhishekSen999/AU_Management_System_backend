package com.au.repository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.au.domain.Demand;
import com.au.domain.DemandMapper;

@Component
public class DemandDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	DemandMapper demandMapper;
	
	public Collection<Demand> getAll()
	{
		String sql = "select * from demand";
		
		Collection<Demand> demandList = jdbcTemplate.query(sql,demandMapper);
		
		return demandList;
	}
	
	public Demand getById(long id)
	{
		
		String sql = "select * from demand where dem_id = ?";
		
		Demand demand = jdbcTemplate.queryForObject(sql,new Object[] {id}, demandMapper);
		
		return demand;
		
	}
	public Collection<Demand> getAllDemandFromHiringManagerWithId(long h_id)
	{
		
		String sql = "select * from demand where h_id = ?";
		Collection<Demand> demandList = jdbcTemplate.query(sql ,new Object[] {h_id},demandMapper);
		
		
		return demandList;
	}
	
	public Collection<Demand> getAllDemandBeforeDate()
	{
		
		
		
		// todo :: -----------------
		
		
		
		
		return null;
	}
	
	
	
	
	
	
	
	
}
