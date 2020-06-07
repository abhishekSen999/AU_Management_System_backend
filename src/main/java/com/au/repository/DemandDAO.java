package com.au.repository;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
	
	public List<Demand> getAll()
	{
		String sql = "select * from demand";
		
		List<Demand> demandList = jdbcTemplate.query(sql,demandMapper);
		
		return demandList;
	}
	
	public Demand getById(long id)
	{
		
		String sql = "select * from demand where dem_id = ?";
		
		Demand demand = jdbcTemplate.queryForObject(sql,new Object[] {id}, demandMapper);
		
		return demand;
		
	}
	public List<Demand> getAllDemandFromHiringManagerWithId(long h_id)
	{
		
		String sql = "select * from demand where h_id = ?";
		List<Demand> demandList = jdbcTemplate.query(sql ,new Object[] {h_id},demandMapper);
		
		
		return demandList;
	}
	
	public List<Demand> getAllDemandBeforeDate(Date date)
	{
		
		String sql = "select * from demand where creation_date <= ? ";
		List<Demand> demandList = jdbcTemplate.query(sql,  new Object[]{date},demandMapper);
		
		return demandList;
		
	}
	
	public List<Demand> getAllDemandAfterDate(Date date)
	{
		
		String sql = "select * from demand where creation_date >= ? ";
		List<Demand> demandList = jdbcTemplate.query(sql,  new Object[]{date},demandMapper);
		
		return demandList;
		
	}
	
	public List<Demand> getAllDemandForLocation(String location)
	{
		
		String sql = "select * from demand where lower(location) = ?";
		List<Demand> demandList = jdbcTemplate.query(sql,  new Object[]{location.toLowerCase()},demandMapper);
		
		return demandList;
		
	}
	
	public List<Demand> getAllDemandWithRequiredPeopleMoreThan(int number_people)
	{
		String sql = "select * from demand where number_people >?";
		List<Demand> demandList = jdbcTemplate.query(sql,  new Object[]{number_people},demandMapper);
		
		return demandList;
		
	}
	

	public List<Demand> getAllDemandWithRequiredPeopleLessThan(int number_people)
	{
		String sql = "select * from demand where number_people <?";
		List<Demand> demandList = jdbcTemplate.query(sql,  new Object[]{number_people},demandMapper);
		
		return demandList;
		
	}
	
	public List<Map<String,Object>> getCountForAllLocation()
	{
		String sql = "select location,count(*) as count from demand group by location";
		List<Map<String,Object>> countLocationList= jdbcTemplate.queryForList(sql);
		return countLocationList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
