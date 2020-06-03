package com.au.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.au.domain.Onboard;
import com.au.domain.Skill;
import com.au.repository.DemandSkillsetDAO;
import com.au.repository.EmployeeSkillsetDAO;
import com.au.repository.OnboardDAO;

@Component
public class OnboardService {

	@Autowired
	OnboardDAO onboardDao;

	@Autowired
	EmployeeSkillsetDAO employeeSkillSetDao;

	@Autowired
	DemandSkillsetDAO demandSkillsetDao;

	public Onboard getById(long onb_id) {

		return onboardDao.getById(onb_id);

	}

	public boolean areSkillsCompatible(long emp_id, long dem_id) {

		List<Skill> employeeSkillList = employeeSkillSetDao.getAllSkillOfEmployeeWithId(emp_id);
		List<Skill> demandSkillList = demandSkillsetDao.getAllSkillForDemandWithId(dem_id);

		for (Skill skill : demandSkillList) {
			if (!employeeSkillList.contains(skill))
				return false;
		}

		return true;

	}

	public List<Onboard> getAll() {
		return onboardDao.getAll();
	}

	public List<Onboard> getByStartDate(Date start_date) {
		return onboardDao.getByStartDate(start_date);
	}

	public List<Onboard> getByEtaOfCompletion(Date eta_of_completion) {
		return onboardDao.getByEtaOfCompletion(eta_of_completion);
	}

	public List<Onboard> getByOnboardingStatus(String onboarding_status) {
		return onboardDao.getByOnboardingStatus(onboarding_status);
	}

	public List<Onboard> getByBgcStatus(String bgc_status) {
		return onboardDao.getByBgcStatus(bgc_status);

	}

	public int add(Onboard onboard) {
		

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

		if (areSkillsCompatible(onboard.getEmp_id(), onboard.getDem_id()))
			return onboardDao.add(onboard);

		return 0;
	}

	public int update(Onboard onboard) {

		// todo: handle multiple onboard of same employee to demand id

		// handling incomplete post request
		if (onboard.getOnb_id() == 0)
			return 0;

		Onboard currentOnboard = onboardDao.getById(onboard.getOnb_id());
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

		if (areSkillsCompatible(currentOnboard.getEmp_id(), currentOnboard.getDem_id()))
			return onboardDao.update(currentOnboard);

		return 0;

	}

	public int delete(long onb_id) {
		return onboardDao.delete(onb_id);
	}

	public Onboard getByEmployeeIdAndDemandId(long emp_id, long dem_id) {
		return onboardDao.getByEmployeeIdAndDemandId(emp_id, dem_id);
	}

}
