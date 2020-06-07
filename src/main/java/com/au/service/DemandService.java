package com.au.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.au.domain.Demand;
import com.au.repository.DemandDAO;

@Component
public class DemandService {
	
	@Autowired
	DemandDAO demandDao;
	
	public Demand getDemandById(long dem_id)
	{
		Demand demand = demandDao.getById(dem_id);
		return demand;
	}
	
	public List<Map<String,Object>> getCountForAllLocation()
	{
		
		List<Map<String,Object>> countLocationList = demandDao.getCountForAllLocation() ;
		return countLocationList;
	}
	
	

}
