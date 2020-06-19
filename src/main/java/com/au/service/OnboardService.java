package com.au.service;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.au.domain.Demand;
import com.au.domain.Onboard;
import com.au.domain.Skill;
import com.au.exception.customExceptions.InvalidDataEntryException;
import com.au.exception.customExceptions.InvalidQueryDataException;
import com.au.repository.OnboardDAO;

@Component
public class OnboardService {

	@Autowired
	OnboardDAO onboardDao;

	@Autowired
	EmployeeSkillset_SkillService employeeSkillset_SkillService;

	@Autowired
	DemandSkillset_SkillService demandSkillset_SkillService;

	@Autowired
	DemandService demandService;

	/**
	 * Optional<String> Object to hold error message. As error message is not for
	 * all requests & error message should contain all the problems in a particular
	 * request hence option object used for avoiding NullPointerException
	 * 
	 */
	

	// for testing
	OnboardDAO getOnboardDao() {
		return onboardDao;
	}

	DemandService getDemandService() {
		return demandService;
	}

	EmployeeSkillset_SkillService getEmployeeSkillset_SkillService() {
		return employeeSkillset_SkillService;
	}

	DemandSkillset_SkillService getDemandSkillset_SkillService() {
		return demandSkillset_SkillService;
	}

	/**
	 * Utility function to check if given demand is met
	 * 
	 * @param dem_id Demand id
	 * @return true if demand is not fulfilled , that is if number of people
	 *         required for demand is less than number of employees onboarded
	 */

	boolean demandNotFullfilled(long dem_id) {
//		
//		if(dem_id <= 0) {
//			errorMessage = Optional.of(" - Demand Id cannot be 0 - ");
//		}
//		
//		errorMessage.ifPresent((message) -> {
//			throw new InvalidQueryDataException(message);
//		});

		Demand demand = getDemandService().getDemandById(dem_id);
		int numberOfOnboards = getOnboardDao().getNumberofOnboardForDemandId(dem_id);
		if (numberOfOnboards < demand.getNumber_people())
			return true;
		return false;

	}

	/**
	 * Function used to test compatibility of skills of given employee with given
	 * demand
	 * 
	 * @param emp_id Employee Id of the employee whose skills needs to be checked
	 *               for compatibility
	 * @param dem_id Demand Id of the demand with whom the compatibility needs to be
	 *               tested
	 * @return true when the employee skillSet is superset of demand skillSet else
	 *         returns false
	 * 
	 * @throws InvalidQueryDataException when either emp_id or dem_id is <=0
	 */
	boolean areSkillsCompatible(long emp_id, long dem_id) {

		Optional<String> errorMessage = Optional.empty();
		
		if (emp_id <= 0) {
			errorMessage = Optional.of(" - Employee Id cannot be 0 - ");
		}
		if (dem_id <= 0) {
			errorMessage = Optional.of(errorMessage.orElse("").concat(" - Demand Id cannot be 0 - "));
		}

		errorMessage.ifPresent((message) -> {
			throw new InvalidQueryDataException(message);
		});

		Set<Skill> employeeSkillList = new HashSet<Skill>();
		Set<Skill> demandSkillList = new HashSet<Skill>();

		employeeSkillList.addAll(getEmployeeSkillset_SkillService().getAllSkillOfEmployeeWithId(emp_id));
		demandSkillList.addAll(getDemandSkillset_SkillService().getAllSkillForDemandWithId(dem_id));

		return employeeSkillList.containsAll(demandSkillList);

	}

	/**
	 * 
	 * @param onb_id Onboard Id which is queried for( Should be >0 ).
	 * @return Onboard object of the particular id from the database through
	 *         repository layer
	 * @throws InvalidQueryDataException when given onb_id <=0
	 */
	public Onboard getById(long onb_id) {
		Optional<String> errorMessage = Optional.empty();

		if (onb_id <= 0) {
			errorMessage = Optional.of(" - Onboard id cannot be 0 - ");

		}

		errorMessage.ifPresent((message) -> {
			throw new InvalidQueryDataException(message);
		});

		return getOnboardDao().getById(onb_id);

	}

	/**
	 * @return List of all onboards from database through repository layer
	 */
	public List<Onboard> getAll() {
		List<Onboard> list = getOnboardDao().getAll();
		return list;
	}

	/**
	 * @param start_date Start Date of onboard( Should not be null )
	 * @return List of all onboard who has the given start_date
	 * @throws InvalidQueryDataException when the given start_date is null
	 */
	public List<Onboard> getByStartDate(Date start_date) {
		Optional<String> errorMessage = Optional.empty();

		if (start_date == null) {
			errorMessage = Optional.of(" - Start Date Cannot be null - ");
		}

		errorMessage.ifPresent((message) -> {
			throw new InvalidQueryDataException(message);
		});

		return getOnboardDao().getByStartDate(start_date);
	}

	/**
	 * @param eta_of_completion ETA of completion of onboard process (Should not be
	 *                          null)
	 * @return List of all onboard who have the given eta_of_completion
	 * @throws InvalidQueryDataException when eta_of_completion is null
	 */
	public List<Onboard> getByEtaOfCompletion(Date eta_of_completion) {
		Optional<String> errorMessage = Optional.empty();

		if (eta_of_completion == null) {
			errorMessage = Optional.of(" - ETA of Completion cannot be null - ");
		}

		errorMessage.ifPresent((message) -> {
			throw new InvalidQueryDataException(message);
		});

		return getOnboardDao().getByEtaOfCompletion(eta_of_completion);
	}

	/**
	 * @param onboarding_status Onboarding Status of onboard (Should not be null)
	 * @return List of all onboard who have given onboarding_status
	 * @throws InvalidQueryDataException if onboarding_status is null
	 */
	public List<Onboard> getByOnboardingStatus(String onboarding_status) {
		Optional<String> errorMessage = Optional.empty();

		if (onboarding_status == null) {
			errorMessage = Optional.of(" - Onboarding Status cannot be null - ");
		}

		errorMessage.ifPresent((message) -> {
			throw new InvalidQueryDataException(message);
		});

		// handling wild cards in request
		if (onboarding_status.contains("*")) {
			onboarding_status = onboarding_status.replace('*', '%');
			return getOnboardDao().getByOnboardingStatusWithWildcard(onboarding_status);
		} else
			return getOnboardDao().getByOnboardingStatus(onboarding_status);
	}

	/**
	 * @param bgc_status BGC Status of the employee for onboard (cannot be null)
	 * @return List of all onboard with given bgc_status
	 * @throws InvalidQueryDataException if bgc_status is null
	 */
	public List<Onboard> getByBgcStatus(String bgc_status) {
		Optional<String> errorMessage = Optional.empty();

		if (bgc_status == null) {
			errorMessage = Optional.of(" - BGC Status cannot be null - ");
		}

		errorMessage.ifPresent((message) -> {
			throw new InvalidQueryDataException(message);
		});

		if (bgc_status.contains("*")) {

			// handling wild cards in request
			bgc_status = bgc_status.replace('*', '%');

			return getOnboardDao().getByBgcStatusWithWildcard(bgc_status);
		}

		else
			return getOnboardDao().getByBgcStatus(bgc_status);

	}

	/**
	 * @param emp_id Employee Id of the employee whose skills needs to be checked
	 *               for compatibility
	 * @param dem_id Demand Id of the demand with whom the compatibility needs to be
	 *               tested
	 * @return true when the employee skillSet is superset of demand skillSet else
	 *         returns false
	 * 
	 * @throws InvalidQueryDataException when either emp_id or dem_id is <=0
	 */
	public Onboard getByEmployeeIdAndDemandId(long emp_id, long dem_id) {
		Optional<String> errorMessage = Optional.empty();
		
		if (emp_id <= 0) {
			errorMessage = Optional.of(" - Employee Id cannot be 0 - ");
		}
		if (dem_id <= 0) {
			errorMessage = Optional.of(errorMessage.orElse("").concat(" - Demand Id cannot be 0 - "));
		}

		errorMessage.ifPresent((message) -> {
			throw new InvalidQueryDataException(message);
		});

		return getOnboardDao().getByEmployeeIdAndDemandId(emp_id, dem_id);
	}

	/**
	 * @param onboard Onboard object which needs to be added to database
	 * @param userEmail identification of user who is doing this operation
	 * @return addedOnboard if add is successful else return null.
	 * @throws InvalidDataEntryException if 1)onboard is null 2)any field of oboard
	 *                                   is invalid 3)demand and employee skillset
	 *                                   are not compatible 4)demand is already met
	 */
	public Onboard add(Onboard onboard , String userEmail) {
		Optional<String> errorMessage = Optional.empty();

		if (onboard == null) {
			errorMessage = Optional.of(" - Cannot add null object - ");
		}

		errorMessage.ifPresent((message) -> {
			throw new InvalidDataEntryException(message);
		});

		Onboard addedOnboard = null;

		if (onboard.getBgc_status() == null) {
			errorMessage = Optional.of(" - BGC Status cannot be null - ");
		}
		if (onboard.getDem_id() <= 0) {
			errorMessage = Optional.of(errorMessage.orElse("").concat(" - Demand Id cannot be null - "));
		}
		if (onboard.getEmp_id() <= 0) {
			errorMessage = Optional.of(errorMessage.orElse("").concat(" - Employee Id cannot be null - "));
		}
		if (onboard.getEta_of_completion() == null) {
			errorMessage = Optional.of(errorMessage.orElse("").concat(" - ETA of Completion cannot be null - "));
		}
		if (onboard.getOnboarding_status() == null) {
			errorMessage = Optional.of(errorMessage.orElse("").concat(" - Onboarding Status cannot be null - "));
		}
		if (onboard.getStart_date() == null) {
			errorMessage = Optional.of(errorMessage.orElse("").concat(" - Start Date cannot be null - "));
		}

		// if emp_id or dem_id is invalid, further checks are not possible
		if (onboard.getEmp_id() <= 0 || onboard.getDem_id() <= 0)
			errorMessage.ifPresent((message) -> {
				throw new InvalidDataEntryException(message);
			});

		if (!areSkillsCompatible(onboard.getEmp_id(), onboard.getDem_id())) {
			errorMessage = Optional.of(errorMessage.orElse("").concat(" - Employee Id: " + onboard.getEmp_id()
					+ " does not have the skills required for Demand Id: " + onboard.getDem_id() + " - "));
		}

		if (!demandNotFullfilled(onboard.getDem_id())) {
			errorMessage = Optional.of(errorMessage.orElse("")
					.concat(" - Demand Id: " + onboard.getDem_id() + " has already been met - "));
		}

		errorMessage.ifPresent((message) -> {
			throw new InvalidDataEntryException(message);
		});

		addedOnboard = getOnboardDao().add(onboard , userEmail);

		return addedOnboard;
	}

	/**
	 * @param onboard Onboard object containing fields which needs to be updated( if
	 *                fields are invalid previous / update not possible then
	 *                previous fields are not changed)
	 * @param userEmail identification of user who is doing this operation
	 * @return the updated Onboard if update operation is successful else null
	 * @throws InvalidDataEntryException if onboard object is null or if onb_id
	 *                                   field of onboard is invalid
	 */

	public Onboard update(Onboard onboard , String userEmail) {
		Optional<String> errorMessage = Optional.empty();

		Onboard updatedOnboard = null;

		if (onboard == null) {
			errorMessage = Optional.of(" - Cannot update null object - ");
		}
		// if object is null further processing is not possible
		errorMessage.ifPresent((message) -> {
			throw new InvalidDataEntryException(message);
		});

		if (onboard.getOnb_id() <= 0) {
			errorMessage = Optional.of(" - Onboard id cannot be 0 - ");
		}
		// if onboard id is 0, further execution/ checks cannot be done
		errorMessage.ifPresent((message) -> {
			throw new InvalidDataEntryException(message);
		});

		// handling incomplete post request
		Onboard currentOnboard = getOnboardDao().getById(onboard.getOnb_id());
		if (onboard.getEmp_id() > 0)
			currentOnboard.setEmp_id(onboard.getEmp_id());

		if (onboard.getDem_id() > 0)
			currentOnboard.setDem_id(onboard.getDem_id());

		if (onboard.getStart_date() != null)
			currentOnboard.setStart_date(onboard.getStart_date());

		if (onboard.getEta_of_completion() != null)
			currentOnboard.setEta_of_completion(onboard.getEta_of_completion());

		if (onboard.getOnboarding_status() != null)
			currentOnboard.setOnboarding_status(onboard.getOnboarding_status());

		if (onboard.getBgc_status() != null)
			currentOnboard.setBgc_status(onboard.getBgc_status());

		if (!areSkillsCompatible(currentOnboard.getEmp_id(), currentOnboard.getDem_id())) {
			errorMessage = Optional.of(errorMessage.orElse("").concat(" - Employee Id: " + currentOnboard.getEmp_id()
					+ " does not have the skills required for Demand Id: " + currentOnboard.getDem_id() + " - "));
		}

		if (!demandNotFullfilled(currentOnboard.getDem_id())) {
			errorMessage = Optional.of(errorMessage.orElse("")
					.concat(" - Demand Id: " + currentOnboard.getDem_id() + " is already met - "));
		}
		
		errorMessage.ifPresent((message) -> {
			throw new InvalidDataEntryException(message);
		});
		
		

		updatedOnboard = getOnboardDao().update(currentOnboard , userEmail);

		return updatedOnboard;

	}

	/**
	 * @param onb_id Onboard Id which has to be deleted (cannot be 0)
	 * @param userEmail identification of user who is doing this operation
	 * @return the deleted Onboard if delete is successful else null
	 * @throws InvalidDataEntryException if onb_id is 0
	 * 
	 */
	public Onboard delete(long onb_id , String userEmail) {
		Optional<String> errorMessage = Optional.empty();

		if (onb_id <= 0) {
			errorMessage = Optional.of(" - Onboard id cannot be 0 - ");
		}

		errorMessage.ifPresent((message) -> {
			throw new InvalidDataEntryException(message);
		});

		Onboard deletedOnboard = getOnboardDao().delete(onb_id , userEmail);

		return deletedOnboard;

	}

}
