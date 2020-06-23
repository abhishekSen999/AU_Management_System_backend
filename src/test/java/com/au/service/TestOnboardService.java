package com.au.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.au.domain.Demand;
import com.au.domain.Onboard;
import com.au.domain.Operation;
import com.au.domain.Skill;
import com.au.exception.customExceptions.InvalidDataEntryException;
import com.au.exception.customExceptions.InvalidQueryDataException;
import com.au.repository.OnboardDAO;

@SpringBootTest
public class TestOnboardService {

	@Spy
	OnboardService onboardServiceSpy;
	
	
	@Mock
	EmployeeSkillset_SkillService employeeSkillset_SkillServiceMock;
	
	@Mock
	DemandSkillset_SkillService demandSkillset_SkillServiceMock;
	
	
	@Mock
	OnboardDAO onboardDaoMock;
	
	
	
	@Mock
	DemandService demandServiceMock;
	
	@BeforeEach
	public void definingSpyBehviour() {
		when(onboardServiceSpy.getOnboardDao()).thenReturn(onboardDaoMock);
		
	}
	
	
	
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
		doReturn(employeeSkillset_SkillServiceMock).when(onboardServiceSpy).getEmployeeSkillset_SkillService();
		doReturn(demandSkillset_SkillServiceMock).when(onboardServiceSpy).getDemandSkillset_SkillService();
		when(employeeSkillset_SkillServiceMock.getAllSkillOfEmployeeWithId(emp_id)).thenReturn(employeeSkillList);
		when(demandSkillset_SkillServiceMock.getAllSkillForDemandWithId(dem_id)).thenReturn(demandSkillList);
		
		
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
		doReturn(employeeSkillset_SkillServiceMock).when(onboardServiceSpy).getEmployeeSkillset_SkillService();
		doReturn(demandSkillset_SkillServiceMock).when(onboardServiceSpy).getDemandSkillset_SkillService();
		Mockito.when(employeeSkillset_SkillServiceMock.getAllSkillOfEmployeeWithId(emp_id)).thenReturn(employeeSkillList);
		Mockito.when(demandSkillset_SkillServiceMock.getAllSkillForDemandWithId(dem_id)).thenReturn(demandSkillList);
		
		
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
		when(onboardDaoMock.getAll()).thenReturn(list);
		
		assertEquals(list,onboardServiceSpy.getAll());
		
		
	}
	
	@Test
	public void testGetByStartDate()
	{
		List<Onboard> list = new ArrayList<Onboard>();
		Date start_date = Date.valueOf("2020-02-01");
		when(onboardServiceSpy.getByStartDate(start_date)).thenReturn(list);
		
		
		
		assertEquals(list,onboardServiceSpy.getByStartDate(start_date));
		
		
	}
	
	

	@Test
	public void testGetByOnboardingStatusNoWildcard()
	{
		List<Onboard> list = new ArrayList<Onboard>();
		String onboarding_status ="started";
		
		when(onboardDaoMock.getByOnboardingStatus(onboarding_status)).thenReturn(list);
		
		
		assertEquals(list,onboardServiceSpy.getByOnboardingStatus(onboarding_status));
		
		
	}
	
	@Test
	public void testGetByOnboardingStatusWithWildcard()
	{
		List<Onboard> list = new ArrayList<Onboard>();
		String onboarding_status ="started*";
		
		when(onboardDaoMock.getByOnboardingStatusWithWildcard(onboarding_status)).thenReturn(list);
		
		
		assertEquals(list,onboardServiceSpy.getByOnboardingStatus(onboarding_status));
		
		
	}
	
	
	@Test
	public void testGetByBgcStatusNoWildcard()
	{
		List<Onboard> list = new ArrayList<Onboard>();
		String bgc_status ="started";
		
		when(onboardDaoMock.getByBgcStatus(bgc_status)).thenReturn(list);
		
		
		assertEquals(list,onboardServiceSpy.getByBgcStatus(bgc_status));
		
		
	}
	 
	@Test
	public void testGetByBgcStatusWithWildcard()
	{
		List<Onboard> list = new ArrayList<Onboard>();
		String bgc_status ="started*";
		
		when(onboardDaoMock.getByBgcStatusWithWildcard(bgc_status)).thenReturn(list);
		
		
		assertEquals(list,onboardServiceSpy.getByBgcStatus(bgc_status));
		
		
	}
	
	
	
	@Test
	public void testAddSuccessful()
	{
		String userEmail = "test_user";
		
		Onboard onboard = new Onboard();
		onboard.setBgc_status("test")
			.setDem_id(2l)
			.setEmp_id(1l)
			.setEta_of_completion(Date.valueOf("2020-02-01"))
			.setOnb_id(1l) // dummy onboard id - 
			.setOnboarding_status("test")
			.setStart_date(Date.valueOf("2020-02-02"));
		
			doReturn(true).when(onboardServiceSpy).areSkillsCompatible(onboard.getEmp_id(),onboard.getDem_id());
			doReturn(true).when(onboardServiceSpy).demandNotFullfilled(onboard.getDem_id());
			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			when(onboardDaoMock.add(onboard,userEmail)).thenReturn(onboard);
			
			assertEquals(onboard,onboardServiceSpy.add(onboard,userEmail));
		
		
	}
	
	
	@Test
	public void testAddFailedAddInDAO()
	{
		
		String userEmail = "test_operator";
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
			
			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			when(onboardDaoMock.add(onboard,userEmail)).thenReturn(null);
			
			
			assertNull(onboardServiceSpy.add(onboard,userEmail));
		
		
	}
	
	
	
	@Test
	public void testAddNonCompatibleSkills()
	{
		
		String userEmail = "test_operator";
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
			
			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			
			Exception exception= assertThrows(InvalidDataEntryException.class, ()->onboardServiceSpy.add(onboard,userEmail));
		
			assertEquals(" - Employee Id: " + onboard.getEmp_id()
				+ " does not have the skills required for Demand Id: " + onboard.getDem_id() + " - " , exception.getMessage());
	}
	
	@Test
	public void testAddDemandAlreadyFulfilled()
	{
		
		String userEmail = "test_operator";
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
			
			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			when(onboardDaoMock.add(onboard,userEmail)).thenReturn(null);
			
			
			Exception exception= assertThrows(InvalidDataEntryException.class, ()->onboardServiceSpy.add(onboard,userEmail));
			
			assertEquals(" - Demand Id: " + onboard.getDem_id() + " has already been met - " , exception.getMessage());
		
			
			
	}
	
	
	
	
	
	
	
	@Test
	public void testAddNoStartDate()
	{
		
		String userEmail = "test_operator";
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
			
			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			when(onboardDaoMock.add(onboard,userEmail)).thenReturn(null);
			
			Exception exception= assertThrows(InvalidDataEntryException.class, ()->onboardServiceSpy.add(onboard,userEmail));
			
			assertEquals( " - Start Date cannot be null - " , exception.getMessage());
			
		
	}
	
	
	
	
	@Test
	public void testAddNoOnboardingStatus()
	{
		
		String userEmail = "test_operator";
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
			
			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			when(onboardDaoMock.add(onboard , userEmail)).thenReturn(null);
			
			
			Exception exception= assertThrows(InvalidDataEntryException.class, ()->onboardServiceSpy.add(onboard,userEmail));
			
			assertEquals( " - Onboarding Status cannot be null - " , exception.getMessage());
			
		
	}
	
	
	@Test
	public void testAddNoEtaOfCompletion()
	{
		
		String userEmail = "test_operator";
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
			
			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			when(onboardDaoMock.add(onboard,userEmail)).thenReturn(null);
			
			Exception exception= assertThrows(InvalidDataEntryException.class, ()->onboardServiceSpy.add(onboard,userEmail));
			
			assertEquals( " - ETA of Completion cannot be null - " , exception.getMessage());
			
			
		
	}
	
	
	
	@Test
	public void testAddZeroEmpId()
	{
		
		String userEmail = "test_operator";
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
			
			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			when(onboardDaoMock.add(onboard,userEmail)).thenReturn(null);
			
			Exception exception= assertThrows(InvalidDataEntryException.class, ()->onboardServiceSpy.add(onboard,userEmail));
			
			assertEquals( " - Employee Id cannot be "+onboard.getEmp_id()+" - " , exception.getMessage());
			
		
	}
	
	
	
	
	@Test
	public void testAddZeroDemId()
	{
		
		String userEmail = "test_operator";
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
			
			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			when(onboardDaoMock.add(onboard,userEmail)).thenReturn(null);
			
			Exception exception= assertThrows(InvalidDataEntryException.class, ()->onboardServiceSpy.add(onboard,userEmail));
			
			assertEquals( " - Demand Id cannot be "+onboard.getDem_id()+" - " , exception.getMessage());
			
			
			
		
	}
	
	
	
	
	@Test
	public void testAddNullBgc()
	{
		
		String userEmail = "test_operator";
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
			
			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			when(onboardDaoMock.add(onboard,userEmail)).thenReturn(null);
			
			Exception exception= assertThrows(InvalidDataEntryException.class, ()->onboardServiceSpy.add(onboard,userEmail));
			
			assertEquals( " - BGC Status cannot be null - " , exception.getMessage());
			
		
	}
	
	
	
	
	
	
	@Test
	public void testUpdate()
	{
		
		String userEmail = "test_user";
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
			
			doReturn(onboard).when(onboardDaoMock).getById(onboard.getOnb_id());
//			doReturn(onboard).when(onboardServiceSpy).getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id());
			when(onboardDaoMock.update(onboard , userEmail)).thenReturn(onboard);
			
			
			assertEquals(onboard,onboardServiceSpy.update(onboard, userEmail));
		
		
	}
	
	@Test
	public void testGetById() {
		Onboard onboard = new Onboard();
		Long onb_id = 1l;
		
		Mockito.when(onboardDaoMock.getById(onb_id)).thenReturn(onboard);
		
		
		assertEquals(onboard,onboardServiceSpy.getById(onb_id));
		
	}
	
	
	@Test
	public void testGetByEtaOfCompletion()
	{
		List<Onboard> list = new ArrayList<Onboard>();
		Date eta_of_completion = Date.valueOf("2020-02-01");
		Mockito.when(onboardDaoMock.getByEtaOfCompletion(eta_of_completion)).thenReturn(list);
		
		
		
		assertEquals(list,onboardServiceSpy.getByEtaOfCompletion(eta_of_completion));
		
		
	}
	
	
	
	@Test
	public void testGetByEmployeeIdAndDemandId() {
		Onboard onboard = new Onboard();
		long emp_id = 1l;
		long dem_id = 1l;
		
		when(onboardDaoMock.getByEmployeeIdAndDemandId(emp_id, dem_id)).thenReturn(onboard);
		
		
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
		
		
		doReturn(demandServiceMock).when(onboardServiceSpy).getDemandService();
		doReturn(demand).when(demandServiceMock).getDemandById(dem_id);
		doReturn(1).when(onboardDaoMock).getNumberofOnboardForDemandId(dem_id);
		
		assertFalse(onboardServiceSpy.demandNotFullfilled(dem_id));
		
		
	}
	
	@Test
	public void testDeleteSuccessful()
	{
		String userEmail = "test_operator";
		
		long onb_id = 1l;
		Onboard onboard = new Onboard();
		onboard.setOnb_id(onb_id); //other fields are not compared in equals
		
		
		doReturn(onboard).when(onboardDaoMock).delete(onb_id,userEmail);
		
		
		assertEquals(onboard, onboardServiceSpy.delete(onb_id, userEmail));
		
	}
	@Test
	public void testDeleteFailed()
	{
		String userEmail = "test_operator";
		long onb_id = 1l;
		doReturn(null).when(onboardDaoMock).delete(onb_id,userEmail );
		
		assertNull( onboardServiceSpy.delete(onb_id , userEmail));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
