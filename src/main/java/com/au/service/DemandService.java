package com.au.service;

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

}
