package com.au.service;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.au.domain.Skill;
import com.au.repository.DemandSkillsetDAO;
import com.au.repository.EmployeeSkillsetDAO;

@SpringBootTest
public class TestOnboardService {

	@Autowired
	OnboardService onboardService;
	
	
	@Mock
	EmployeeSkillsetDAO employeeSkillsetDao;
	
	@Mock
	DemandSkillsetDAO demandSkillsetDao;
	
	@Test
	public void testAreSkillsCompatibleTrue()
	{
		
		Skill skill1 = new Skill();
		skill1.setSkill_id(1)
			.setSkill_name("test_skill1");
		
		Skill skill2 = new Skill();
		skill2.setSkill_id(2)
			.setSkill_name("test_skill2");
		
		
		Skill skill3 = new Skill();
		skill3.setSkill_id(3)
			.setSkill_name("test_skill3");
		
		
		List<Skill> employeeSkillList = new ArrayList<Skill>();
		employeeSkillList.add(skill1);
		employeeSkillList.add(skill2);
		employeeSkillList.add(skill3);
		
		List<Skill> demandSkillList = new ArrayList<Skill>();
		demandSkillList.add(skill1);
		demandSkillList.add(skill2);
		
		long emp_id = 1;
		long dem_id = 2;
		
		//defining mock behaviour
		Mockito.when(employeeSkillsetDao.getAllSkillOfEmployeeWithId(emp_id)).thenReturn(employeeSkillList);
		Mockito.when(demandSkillsetDao.getAllSkillForDemandWithId(dem_id)).thenReturn(demandSkillList);
		
		
		assertTrue(onboardService.areSkillsCompatible(emp_id, dem_id));
			
	}
	@Test
	public void testAreSkillsCompatibleFalse()
	{
		
		Skill skill1 = new Skill();
		skill1.setSkill_id(1)
			.setSkill_name("test_skill1");
		
		Skill skill2 = new Skill();
		skill2.setSkill_id(2)
			.setSkill_name("test_skill2");
		
		
		Skill skill3 = new Skill();
		skill3.setSkill_id(3)
			.setSkill_name("test_skill3");
		
		
		List<Skill> employeeSkillList = new ArrayList<Skill>();
		employeeSkillList.add(skill1);
		employeeSkillList.add(skill2);
		
		List<Skill> demandSkillList = new ArrayList<Skill>();
		demandSkillList.add(skill1);
		demandSkillList.add(skill2);
		demandSkillList.add(skill3);
		
		long emp_id = 1;
		long dem_id = 2;
		
		//defining mock behaviour
		Mockito.when(employeeSkillsetDao.getAllSkillOfEmployeeWithId(emp_id)).thenReturn(employeeSkillList);
		Mockito.when(demandSkillsetDao.getAllSkillForDemandWithId(dem_id)).thenReturn(demandSkillList);
		
		
		assertTrue(onboardService.areSkillsCompatible(emp_id, dem_id));
		
		
	}
	
//	@Test
//	public void testAreSkillsCompitableDatabase()
//	{
//		assertFalse(onboardService.areSkillsCompatible(4L,2L));
//	}
	
	
	
	
	
	
	
	
}
