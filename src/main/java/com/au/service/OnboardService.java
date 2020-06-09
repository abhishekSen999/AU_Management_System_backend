package com.au.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.au.domain.Demand;
import com.au.domain.Onboard;
import com.au.domain.Operation;
import com.au.domain.Skill;


import com.au.repository.OnboardDAO;

@Component
public class OnboardService {

	@Autowired
	OnboardLogService onboardLogService;
	
	

	@Autowired
	OnboardDAO onboardDao;

	@Autowired
	EmployeeSkillset_SkillService employeeSkillset_SkillService;
	
	@Autowired
	DemandSkillset_SkillService demandSkillset_SkillService;
	
	@Autowired
	DemandService demandService;
	
	
	
	
	
	
	
	
	// for testing testing
	OnboardDAO getOnboardDao() {
		return onboardDao;
	}
	
	OnboardLogService getOnboardLogService()
	{
		return onboardLogService;
	}
	
	DemandService getDemandService()
	{
		return demandService;
	}
	
	EmployeeSkillset_SkillService getEmployeeSkillset_SkillService() {
		return employeeSkillset_SkillService;
	}
	
	DemandSkillset_SkillService getDemandSkillset_SkillService() {
		return demandSkillset_SkillService;
	}
	
	
	
	
	
	
	

	public Onboard getById(long onb_id) {

		return getOnboardDao().getById(onb_id); 

	}

	public boolean areSkillsCompatible(long emp_id, long dem_id) {

		List<Skill> employeeSkillList = getEmployeeSkillset_SkillService().getAllSkillOfEmployeeWithId(emp_id);
		List<Skill> demandSkillList = getDemandSkillset_SkillService().getAllSkillForDemandWithId(dem_id);

		for (Skill skill : demandSkillList) {
			if (!employeeSkillList.contains(skill))
				return false;
		}

		return true;

	}

	public List<Onboard> getAll() {
		List<Onboard> list = getOnboardDao().getAll();
		return list;
	}

	public List<Onboard> getByStartDate(Date start_date) {
		return getOnboardDao().getByStartDate(start_date);
	}

	public List<Onboard> getByEtaOfCompletion(Date eta_of_completion) {
		return getOnboardDao().getByEtaOfCompletion(eta_of_completion);
	}

	public List<Onboard> getByOnboardingStatus(String onboarding_status) {

		// handling wild cards in request
		if (onboarding_status.contains("*")) {
			onboarding_status = onboarding_status.replace('*', '%');
			return getOnboardDao().getByOnboardingStatusWithWildcard(onboarding_status);
		} else
			return getOnboardDao().getByOnboardingStatus(onboarding_status);
	}

	public List<Onboard> getByBgcStatus(String bgc_status) {

		if (bgc_status.contains("*")) {

			// handling wild cards in request
			bgc_status = bgc_status.replace('*', '%');

			return getOnboardDao().getByBgcStatusWithWildcard(bgc_status);
		}

		else
			return getOnboardDao().getByBgcStatus(bgc_status);

	}

	public Object add(Onboard onboard) {

		int result = 0;

		if (onboard.getBgc_status() == null)
			return 0;
		if (onboard.getDem_id() == 0)
			return 0;
		if (onboard.getEmp_id() == 0)
			return 0;
		if (onboard.getEta_of_completion() == null)
			return 0;
		if (onboard.getOnboarding_status() == null) 
			return 0;
		if (onboard.getStart_date() == null)
			return 0;

		if (! areSkillsCompatible(onboard.getEmp_id(), onboard.getDem_id()))
				return "Skills Not Compatible";
		if(! demandNotFullfilled(onboard.getDem_id()))
				return "Demand Already Fulfilled";
		
		result = getOnboardDao().add(onboard);

		if (result == 1) // upon successful add operation create log
		{
			long onb_id = this.getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id()).getOnb_id();

			return getOnboardLogService().setLog(Operation.add, onb_id);
		}

		return result;
	}

	public Onboard getByEmployeeIdAndDemandId(long emp_id, long dem_id) {
		return getOnboardDao().getByEmployeeIdAndDemandId(emp_id, dem_id);
	}

	
	boolean demandNotFullfilled(long dem_id)
	{
		Demand demand = getDemandService().getDemandById(dem_id);
		int numberOfOnboards = getOnboardDao().getNumberofOnboardForDemandId(dem_id);
		if(numberOfOnboards<demand.getNumber_people())
			return true;
		return false; 
		
		
	}
	
	
	public Object update(Onboard onboard) {

		// todo: handle multiple onboard of same employee to demand id

		// handling incomplete post request

		int result = -1;
		if (onboard.getOnb_id() == 0)
			return 0;

		Onboard currentOnboard = getOnboardDao().getById(onboard.getOnb_id());
		if (onboard.getEmp_id() != 0)
			currentOnboard.setEmp_id(onboard.getEmp_id());

		if (onboard.getDem_id() != 0)
			currentOnboard.setDem_id(onboard.getDem_id());

		if (onboard.getStart_date() != null)
			currentOnboard.setStart_date(onboard.getStart_date());

		if (onboard.getEta_of_completion() != null)
			currentOnboard.setEta_of_completion(onboard.getEta_of_completion());

		if (onboard.getOnboarding_status() != null)
			currentOnboard.setOnboarding_status(onboard.getOnboarding_status());

		if (onboard.getBgc_status() != null)
			currentOnboard.setBgc_status(onboard.getBgc_status());

		if (! areSkillsCompatible(currentOnboard.getEmp_id(), currentOnboard.getDem_id()))
			return "Skills Are Not Compatible";
				
				
		if (! demandNotFullfilled(currentOnboard.getDem_id()))
			return "Demand Already Fulfilled";
		
		result = getOnboardDao().update(currentOnboard);

		if (result == 1)// upon successful operation create log
		{
			getOnboardLogService().setLog(Operation.update, currentOnboard.getOnb_id());
		}

		return result;

	}

	public Object delete(long onb_id) {
		int result = getOnboardDao().delete(onb_id);

		if (result == 1)
			return getOnboardLogService().setLog(Operation.delete, onb_id);

		return result;

	}

}
