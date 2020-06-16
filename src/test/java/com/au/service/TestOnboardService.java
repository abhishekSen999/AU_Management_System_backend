package com.au.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.au.customExceptions.InvalidQueryDataException;
import com.au.domain.Demand;
import com.au.domain.Onboard;
import com.au.domain.Operation;
import com.au.domain.Skill;

import com.au.repository.OnboardDAO;

@SpringBootTest
public class TestOnboardService {

	@Spy
	OnboardService onboardServiceSpy;
	
	
	@Mock
	EmployeeSkillset_SkillService employeeSkillset_SkillService;
	
	@Mock
	DemandSkillset_SkillService demandSkillset_SkillService;
	
	
	@Mock
	OnboardDAO onboardDao;
	
	@Mock
	OnboardLogService onboardLogService;
	
	@Mock
	DemandService demandService;
	
	
	
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
		
		long emp_id = 4;
		long dem_id = 2;
		
		//defining mock behaviour
		doReturn(employeeSkillset_SkillService).when(onboardServiceSpy).getEmployeeSkillset_SkillService();
		doReturn(demandSkillset_SkillService).when(onboardServiceSpy).getDemandSkillset_SkillService();
		Mockito.when(employeeSkillset_SkillService.getAllSkillOfEmployeeWithId(emp_id)).thenReturn(employeeSkillList);
		Mockito.when(demandSkillset_SkillService.getAllSkillForDemandWithId(dem_id)).thenReturn(demandSkillList);
		
		
		assertTrue(onboardServiceSpy.areSkillsCompatible(emp_id, dem_id));
			
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
		doReturn(employeeSkillset_SkillService).when(onboardServiceSpy).getEmployeeSkillset_SkillService();
		doReturn(demandSkillset_SkillService).when(onboardServiceSpy).getDemandSkillset_SkillService();
		Mockito.when(employeeSkillset_SkillService.getAllSkillOfEmployeeWithId(emp_id)).thenReturn(employeeSkillList);
		Mockito.when(demandSkillset_SkillService.getAllSkillForDemandWithId(dem_id)).thenReturn(demandSkillList);
		
		
		assertFalse(onboardServiceSpy.areSkillsCompatible(emp_id, dem_id));
		
		
	}
	
	@Test
	public void testAreSkillsCompatibleThrowsExceptionForZeroEmpId()
	{
		 
		long emp_id = 0;
		long dem_id = 1l;
		
		Exception e = assertThrows(InvalidQueryDataException.class, ()->onboardServiceSpy.areSkillsCompatible(emp_id, dem_id));
		System.out.println(e.getMessage());
		assertEquals(" - Employee Id cannot be 0 - ",e.getMessage());
		
	}
	
	@Test
	public void testAreSkillsCompatibleThrowsExceptionForZeroDemId()
	{
		 
		long emp_id = 1l;
		long dem_id = 0;
		
		Exception e = assertThrows(InvalidQueryDataException.class, ()->onboardServiceSpy.areSkillsCompatible(emp_id, dem_id));
		System.out.println(e.getMessage());
		assertEquals(" - Demand Id cannot be 0 - ",e.getMessage());
		
	}
	
	@Test
	public void testAreSkillsCompatibleThrowsExceptionForZeroEmpIdZeroDemId()
	{
		 
		long emp_id = 0;
		long dem_id = 0;
		
		Exception e = assertThrows(InvalidQueryDataException.class, ()->onboardServiceSpy.areSkillsCompatible(emp_id, dem_id));
		System.out.println(e.getMessage());
		assertEquals(" - Employee Id cannot be 0 -  - Demand Id cannot be 0 - ",e.getMessage());
		
	}
	
	
	@Test
	public void testGetAll()
	{
		List<Onboard> list =null;
		Mockito.when(onboardDao.getAll()).thenReturn(list);
		doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
		
		assertEquals(list,onboardServiceSpy.getAll());
		
		
	}
	
	@Test
	public void testGetByStartDate()
	{
		List<Onboard> list = new ArrayList<Onboard>();
		Date start_date = Date.valueOf("2020-02-01");
		doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
		Mockito.when(onboardServiceSpy.getByStartDate(start_date)).thenReturn(list);
		
		
		
		assertEquals(list,onboardServiceSpy.getByStartDate(start_date));
		
		
	}
	
	

	@Test
	public void testGetByOnboardingStatusNoWildcard()
	{
		List<Onboard> list = new ArrayList<Onboard>();
		String onboarding_status ="started";
		
		doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
		Mockito.when(onboardDao.getByOnboardingStatus(onboarding_status)).thenReturn(list);
		
		
		assertEquals(list,onboardServiceSpy.getByOnboardingStatus(onboarding_status));
		
		
	}
	
	@Test
	public void testGetByOnboardingStatusWithWildcard()
	{
		List<Onboard> list = new ArrayList<Onboard>();
		String onboarding_status ="started*";
		
		doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
		Mockito.when(onboardDao.getByOnboardingStatusWithWildcard(onboarding_status)).thenReturn(list);
		
		
		assertEquals(list,onboardServiceSpy.getByOnboardingStatus(onboarding_status));
		
		
	}
	
	
	@Test
	public void testGetByBgcStatusNoWildcard()
	{
		List<Onboard> list = new ArrayList<Onboard>();
		String bgc_status ="started";
		
		doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
		Mockito.when(onboardDao.getByBgcStatus(bgc_status)).thenReturn(list);
		
		
		assertEquals(list,onboardServiceSpy.getByBgcStatus(bgc_status));
		
		
	}
	 
	@Test
	public void testGetByBgcStatusWithWildcard()
	{
		List<Onboard> list = new ArrayList<Onboard>();
		String bgc_status ="started*";
		
		doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
		Mockito.when(onboardDao.getByBgcStatusWithWildcard(bgc_status)).thenReturn(list);
		
		
		assertEquals(list,onboardServiceSpy.getByBgcStatus(bgc_status));
		
		
	}
	
	
	
	@Test
	public void testAdd()
	{
		
		
		Onboard onboard = new Onboard();
		onboard.setBgc_status("test")
			.setDem_id(2l)
			.setEmp_id(1l)
			.setEta_of_completion(Date.valueOf("2020-02-01"))
			.setOnb_id(0)
			.setOnboarding_status("test")
			.setStart_date(Date.valueOf("2020-02-02"));
		
			doReturn(true).when(onboardServiceSpy).areSkillsCompatible(onboard.getEmp_id(),onboard.getDem_id());
			doReturn(true).when(onboardServiceSpy).demandNotFullfilled(onboard.getDem_id());
			doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
			doReturn(onboardLogService).when(onboardServiceSpy).getOnboardLogService();
			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			Mockito.when(onboardDao.add(onboard)).thenReturn(1);
			
			Mockito.when(onboardLogService.setLog(Operation.add,0l)).thenReturn(1);
			
			assertEquals(1,onboardServiceSpy.add(onboard));
		
		
	}
	
	
	@Test
	public void testAddFailedAddInDAO()
	{
		
		
		Onboard onboard = new Onboard();
		onboard.setBgc_status("test")
			.setDem_id(2l)
			.setEmp_id(1l)
			.setEta_of_completion(Date.valueOf("2020-02-01"))
			.setOnb_id(0)
			.setOnboarding_status("test")
			.setStart_date(Date.valueOf("2020-02-02"));
		
			doReturn(true).when(onboardServiceSpy).areSkillsCompatible(onboard.getEmp_id(),onboard.getDem_id());
			doReturn(true).when(onboardServiceSpy).demandNotFullfilled(onboard.getDem_id());
			doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
			doReturn(onboardLogService).when(onboardServiceSpy).getOnboardLogService();
			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			Mockito.when(onboardDao.add(onboard)).thenReturn(0);
			
			Mockito.when(onboardLogService.setLog(Operation.add,0l)).thenReturn(1);
			
			assertEquals(0,onboardServiceSpy.add(onboard));
		
		
	}
	
	
	
	@Test
	public void testAddNonCompatibleSkills()
	{
		
		
		Onboard onboard = new Onboard();
		onboard.setBgc_status("test")
			.setDem_id(2l)
			.setEmp_id(1l)
			.setEta_of_completion(Date.valueOf("2020-02-01"))
			.setOnb_id(0)
			.setOnboarding_status("test")
			.setStart_date(Date.valueOf("2020-02-02"));
		
			doReturn(false).when(onboardServiceSpy).areSkillsCompatible(onboard.getEmp_id(),onboard.getDem_id());
			doReturn(true).when(onboardServiceSpy).demandNotFullfilled(onboard.getDem_id());
			doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
			doReturn(onboardLogService).when(onboardServiceSpy).getOnboardLogService();
			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			Mockito.when(onboardDao.add(onboard)).thenReturn(1);
			
			Mockito.when(onboardLogService.setLog(Operation.add,0l)).thenReturn(1);
			
			assertTrue("Skills Not Compatible".contentEquals((String)onboardServiceSpy.add(onboard)));
		
		
	}
	
	@Test
	public void testAddDemandAlreadyFulfilled()
	{
		
		
		Onboard onboard = new Onboard();
		onboard.setBgc_status("test")
			.setDem_id(2l)
			.setEmp_id(1l)
			.setEta_of_completion(Date.valueOf("2020-02-01"))
			.setOnb_id(0)
			.setOnboarding_status("test")
			.setStart_date(Date.valueOf("2020-02-02"));
		
			doReturn(true).when(onboardServiceSpy).areSkillsCompatible(onboard.getEmp_id(),onboard.getDem_id());
			doReturn(false).when(onboardServiceSpy).demandNotFullfilled(onboard.getDem_id());
			doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
			doReturn(onboardLogService).when(onboardServiceSpy).getOnboardLogService();
			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			Mockito.when(onboardDao.add(onboard)).thenReturn(1);
			
			Mockito.when(onboardLogService.setLog(Operation.add,0l)).thenReturn(1);
			
			assertTrue("Demand Already Fulfilled".contentEquals((String)onboardServiceSpy.add(onboard)));
		
		
	}
	
	
	
	
	
	
	
	@Test
	public void testAddNoStartDate()
	{
		
		
		Onboard onboard = new Onboard();
		onboard.setBgc_status("test")
			.setDem_id(2l)
			.setEmp_id(1l)
			.setEta_of_completion(Date.valueOf("2020-02-01"))
			.setOnb_id(0)
			.setOnboarding_status("test");
//			.setStart_date(Date.valueOf("2020-02-02"));
		
			doReturn(true).when(onboardServiceSpy).areSkillsCompatible(onboard.getEmp_id(),onboard.getDem_id());
			doReturn(true).when(onboardServiceSpy).demandNotFullfilled(onboard.getDem_id());
			doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
			doReturn(onboardLogService).when(onboardServiceSpy).getOnboardLogService();
			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			Mockito.when(onboardDao.add(onboard)).thenReturn(1);
			
			Mockito.when(onboardLogService.setLog(Operation.add,0l)).thenReturn(1);
			
			assertEquals(0,onboardServiceSpy.add(onboard));
		
		
	}
	
	
	
	
	@Test
	public void testAddNoOnboardingStatus()
	{
		
		
		Onboard onboard = new Onboard();
		onboard.setBgc_status("test")
			.setDem_id(2l)
			.setEmp_id(1l)
			.setEta_of_completion(Date.valueOf("2020-02-01"))
			.setOnb_id(0)
//			.setOnboarding_status("test")
			.setStart_date(Date.valueOf("2020-02-02"));
		
			doReturn(true).when(onboardServiceSpy).areSkillsCompatible(onboard.getEmp_id(),onboard.getDem_id());
			doReturn(true).when(onboardServiceSpy).demandNotFullfilled(onboard.getDem_id());
			doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
			doReturn(onboardLogService).when(onboardServiceSpy).getOnboardLogService();
			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			Mockito.when(onboardDao.add(onboard)).thenReturn(1);
			
			Mockito.when(onboardLogService.setLog(Operation.add,0l)).thenReturn(1);
			
			assertEquals(0,onboardServiceSpy.add(onboard));
		
		
	}
	
	
	@Test
	public void testAddNoEtaOdCompletion()
	{
		
		
		Onboard onboard = new Onboard();
		onboard.setBgc_status("test")
			.setDem_id(2l)
			.setEmp_id(1l)
//			.setEta_of_completion(Date.valueOf("2020-02-01"))
			.setOnb_id(0)
			.setOnboarding_status("test")
			.setStart_date(Date.valueOf("2020-02-02"));
		
			doReturn(true).when(onboardServiceSpy).areSkillsCompatible(onboard.getEmp_id(),onboard.getDem_id());
			doReturn(true).when(onboardServiceSpy).demandNotFullfilled(onboard.getDem_id());
			doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
			doReturn(onboardLogService).when(onboardServiceSpy).getOnboardLogService();
			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			Mockito.when(onboardDao.add(onboard)).thenReturn(1);
			
			Mockito.when(onboardLogService.setLog(Operation.add,0l)).thenReturn(1);
			
			assertEquals(0,onboardServiceSpy.add(onboard));
		
		
	}
	
	
	
	@Test
	public void testAddZeroEmpId()
	{
		
		
		Onboard onboard = new Onboard();
		onboard.setBgc_status("test")
			.setDem_id(2l)
//			.setEmp_id(1l)
			.setEta_of_completion(Date.valueOf("2020-02-01"))
			.setOnb_id(0)
			.setOnboarding_status("test")
			.setStart_date(Date.valueOf("2020-02-02"));
		
			doReturn(true).when(onboardServiceSpy).areSkillsCompatible(onboard.getEmp_id(),onboard.getDem_id());
			doReturn(true).when(onboardServiceSpy).demandNotFullfilled(onboard.getDem_id());
			doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
			doReturn(onboardLogService).when(onboardServiceSpy).getOnboardLogService();
			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			Mockito.when(onboardDao.add(onboard)).thenReturn(1);
			
			Mockito.when(onboardLogService.setLog(Operation.add,0l)).thenReturn(1);
			
			assertEquals(0,onboardServiceSpy.add(onboard));
		
		
	}
	
	
	
	
	@Test
	public void testAddZeroDemId()
	{
		
		
		Onboard onboard = new Onboard();
		onboard.setBgc_status("test")
//			.setDem_id(2l)
			.setEmp_id(1l)
			.setEta_of_completion(Date.valueOf("2020-02-01"))
			.setOnb_id(0)
			.setOnboarding_status("test")
			.setStart_date(Date.valueOf("2020-02-02"));
		
			doReturn(true).when(onboardServiceSpy).areSkillsCompatible(onboard.getEmp_id(),onboard.getDem_id());
			doReturn(true).when(onboardServiceSpy).demandNotFullfilled(onboard.getDem_id());
			doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
			doReturn(onboardLogService).when(onboardServiceSpy).getOnboardLogService();
			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			Mockito.when(onboardDao.add(onboard)).thenReturn(1);
			
			Mockito.when(onboardLogService.setLog(Operation.add,0l)).thenReturn(1);
			
			assertEquals(0,onboardServiceSpy.add(onboard));
		
		
	}
	
	
	
	
	@Test
	public void testAddNullBgc()
	{
		
		
		Onboard onboard = new Onboard();
		onboard//.setBgc_status("test")
			.setDem_id(2l)
			.setEmp_id(1l)
			.setEta_of_completion(Date.valueOf("2020-02-01"))
			.setOnb_id(0)
			.setOnboarding_status("test")
			.setStart_date(Date.valueOf("2020-02-02"));
		
			doReturn(true).when(onboardServiceSpy).areSkillsCompatible(onboard.getEmp_id(),onboard.getDem_id());
			doReturn(true).when(onboardServiceSpy).demandNotFullfilled(onboard.getDem_id());
			doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
			doReturn(onboardLogService).when(onboardServiceSpy).getOnboardLogService();
			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			Mockito.when(onboardDao.add(onboard)).thenReturn(1);
			
			Mockito.when(onboardLogService.setLog(Operation.add,0l)).thenReturn(1);
			
			assertEquals(0,onboardServiceSpy.add(onboard));
		
		
	}
	
	
	
	
	
	
	@Test
	public void testUpdate()
	{
		
		
		Onboard onboard = new Onboard();
		onboard.setBgc_status("test")
			.setDem_id(2l)
			.setEmp_id(1l)
			.setEta_of_completion(Date.valueOf("2020-02-01"))
			.setOnb_id(1l)
			.setOnboarding_status("test")
			.setStart_date(Date.valueOf("2020-02-02"));
		
			doReturn(true).when(onboardServiceSpy).areSkillsCompatible(onboard.getEmp_id(),onboard.getDem_id());
			doReturn(true).when(onboardServiceSpy).demandNotFullfilled(onboard.getDem_id());
			doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
			doReturn(onboardLogService).when(onboardServiceSpy).getOnboardLogService();
			doReturn(onboard).when(onboardDao).getById(onboard.getOnb_id());
//			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			Mockito.when(onboardDao.update(onboard)).thenReturn(1);
			
			Mockito.when(onboardLogService.setLog(Operation.update,0l)).thenReturn(1);
			
			assertEquals(1,onboardServiceSpy.update(onboard));
		
		
	}
	
	@Test
	public void testGetById() {
		Onboard onboard = new Onboard();
		Long onb_id = 1l;
		
		doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
		Mockito.when(onboardDao.getById(onb_id)).thenReturn(onboard);
		
		
		assertEquals(onboard,onboardServiceSpy.getById(onb_id));
		
	}
	
	
	@Test
	public void testGetByEtaOfCompletion()
	{
		List<Onboard> list = new ArrayList<Onboard>();
		Date eta_of_completion = Date.valueOf("2020-02-01");
		doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
		Mockito.when(onboardServiceSpy.getByEtaOfCompletion(eta_of_completion)).thenReturn(list);
		
		
		
		assertEquals(list,onboardServiceSpy.getByEtaOfCompletion(eta_of_completion));
		
		
	}
	
	
	
	@Test
	public void testGetByEmployeeIdAndDemandId() {
		Onboard onboard = new Onboard();
		long emp_id = 0l;
		long dem_id = 0l;
		
		doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
		Mockito.when(onboardDao.getByEmployeeIdAndDemandId(emp_id, dem_id)).thenReturn(onboard);
		
		
		assertEquals(onboard,onboardServiceSpy.getByEmployeeIdAndDemandId(emp_id, dem_id));
		
	}
	
	@Test
	public void testDemandNotFullfilledFalse()
	{
		long dem_id = 0l;
		Demand demand = new Demand();
		demand.setCreation_date(Date.valueOf("2020-02-02"))
			.setDem_id(dem_id)
			.setExperience_requirement(2)
			.setH_id(0l)
			.setLoction("test")
			.setNumber_people(1);
		
		
		doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
		doReturn(demandService).when(onboardServiceSpy).getDemandService();
		doReturn(demand).when(demandService).getDemandById(dem_id);
		doReturn(1).when(onboardDao).getNumberofOnboardForDemandId(dem_id);
		
		assertFalse(onboardServiceSpy.demandNotFullfilled(dem_id));
		
		
	}
	
	@Test
	public void testDeleteSuccessful()
	{
		long onb_id = 1l;
		doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
		doReturn(1).when(onboardDao).delete(onb_id);
		doReturn(onboardLogService).when(onboardServiceSpy).getOnboardLogService();
		doReturn(1).when(onboardLogService).setLog(Operation.delete, onb_id);
		
		assertEquals(1, onboardServiceSpy.delete(onb_id));
		
	}
	@Test
	public void testDeleteFailed()
	{
		long onb_id = 1l;
		doReturn(onboardDao).when(onboardServiceSpy).getOnboardDao();
		doReturn(0).when(onboardDao).delete(onb_id);
		doReturn(onboardLogService).when(onboardServiceSpy).getOnboardLogService();
		doReturn(1).when(onboardLogService).setLog(Operation.delete, onb_id);
		assertEquals(0, onboardServiceSpy.delete(onb_id));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
