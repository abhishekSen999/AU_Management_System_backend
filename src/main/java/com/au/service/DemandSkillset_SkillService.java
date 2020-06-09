package com.au.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.au.domain.Skill;
import com.au.repository.DemandSkillset_SkillDAO;

@Component
public class DemandSkillset_SkillService {

	
	@Autowired
	DemandSkillset_SkillDAO demandSkillset_SkillDAO;
	
	DemandSkillset_SkillDAO getDemandSkillset_SkillDAO(){
		return demandSkillset_SkillDAO;
	}
	
	
	public List<Skill> getAllSkillForDemandWithId(long dem_id){
		return getDemandSkillset_SkillDAO().getAllSkillForDemandWithId(dem_id);
	}
	
}
