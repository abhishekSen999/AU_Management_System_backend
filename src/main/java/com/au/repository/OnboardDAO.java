package com.au.repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.au.domain.Onboard;
import com.au.domain.OnboardMapper;
import com.au.domain.Operation;
import com.au.exception.customExceptions.FailedDatabaseLoggingException;
import com.au.exception.customExceptions.InvalidDataEntryException;
import com.au.exception.customExceptions.RecordNotFoundException;
import com.au.service.OnboardLogService;

@Component
public class OnboardDAO {

	private final int maxDatabaseLogginAttempts = 3;

	@Autowired
	OnboardMapper onboardMapper;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	OnboardLogService onboardLogService;

	// making class testable
	OnboardLogService getOnboardLogService() {
		return onboardLogService;
	}

	public List<Onboard> getAll() {
		String sql = "select * from onboard";
		List<Onboard> onboardList = jdbcTemplate.query(sql, onboardMapper);
		
		
		if(onboardList.size()==0) {
			throw new RecordNotFoundException("No Records Found");
		}
		
		return onboardList;
	}

	// todo add rest of the layers
	public List<Onboard> getByStartDate(Date start_date) {
		String sql = "select * from onboard where start_date=?";
		List<Onboard> onboardList = jdbcTemplate.query(sql, new Object[] { start_date }, onboardMapper);
		
		if(onboardList.size()==0) {
			throw new RecordNotFoundException("No Records Found for Start Date: "+start_date);
		}
		
		return onboardList;
	}

	public List<Onboard> getByEtaOfCompletion(Date eta_of_completion) {
		String sql = "select * from onboard where eta_of_completion=?";
		List<Onboard> onboardList = jdbcTemplate.query(sql, new Object[] { eta_of_completion }, onboardMapper);
		if(onboardList.size()==0) {
			throw new RecordNotFoundException("No Records Found for Eta Of Completion: "+eta_of_completion);
		}
		
		return onboardList;
	}

	public List<Onboard> getByOnboardingStatus(String onboarding_status) {
		String sql = "select * from onboard where lower(onboarding_status) = ? ";
		List<Onboard> onboardList = jdbcTemplate.query(sql, new Object[] { onboarding_status.toLowerCase() },
				onboardMapper);
		
		if(onboardList.size()==0) {
			throw new RecordNotFoundException("No Records Found for Onboarding Status: "+onboarding_status);
		}
		
		return onboardList;

	}

	public List<Onboard> getByOnboardingStatusWithWildcard(String onboarding_status) {
		String sql = "select * from onboard where lower(onboarding_status) like ? ";
		List<Onboard> onboardList = jdbcTemplate.query(sql, new Object[] { onboarding_status.toLowerCase() },
				onboardMapper);
		if(onboardList.size()==0) {
			throw new RecordNotFoundException("No Records Found for Onboarding Status like : "+onboarding_status.replace('%', '*'));
		}
		
		return onboardList;

	}

	public List<Onboard> getByBgcStatus(String bgc_status) {
		String sql = "select * from onboard where lower(bgc_status) = ?";
		List<Onboard> onboardList = jdbcTemplate.query(sql, new Object[] { bgc_status.toLowerCase() }, onboardMapper);
		
		
		if(onboardList.size()==0) {
			throw new RecordNotFoundException("No Records Found for BGC Status: "+bgc_status);
		}
		
		return onboardList;

	}

	public List<Onboard> getByBgcStatusWithWildcard(String bgc_status) {
		String sql = "select * from onboard where lower(bgc_status) like ?";
		List<Onboard> onboardList = jdbcTemplate.query(sql, new Object[] { bgc_status.toLowerCase() }, onboardMapper);
		
		if(onboardList.size()==0) {
			throw new RecordNotFoundException("No Records Found for BGC Status like : "+bgc_status.replace('%', '*'));
		}
		
		return onboardList;

	}

	public int getNumberofOnboardForDemandId(long dem_id) {
		String sql = "select count(*) from onboard where dem_id = ?";
		List<Map<String, Object>> countMap = jdbcTemplate.queryForList(sql, new Object[] { dem_id });
		int count = (int) (long) countMap.get(0).get("count(*)");
		return count;

	}

	public Onboard getById(long onb_id) {

		String sql = "select * from onboard where onb_id = ?";
		
		
		Onboard onboard = null;
		try {
			onboard = jdbcTemplate.queryForObject(sql, new Object[] { onb_id }, onboardMapper);
		} 
		catch (EmptyResultDataAccessException exception) {
			throw new RecordNotFoundException("No Record found for Onboard Id: "+onb_id , exception);
		}
		

		
		
		
		return onboard;
	}

	public Onboard getByEmployeeIdAndDemandId(long emp_id, long dem_id) {

		String sql = "select * from onboard where emp_id=? and dem_id =?";
		Onboard onboard = null;
		try {
			onboard = jdbcTemplate.queryForObject(sql, new Object[] { emp_id, dem_id }, onboardMapper);
		}
		catch (EmptyResultDataAccessException exception) {
			throw new RecordNotFoundException("No Record found for Employee Id: "+emp_id+" and Demand Id: "+dem_id , exception);
		}
		
		
		return onboard;
	}
	

	
	
	
    @Transactional
	public int add(Onboard onboard , String userEmail) {
		int result = 0;
		String sql = "insert into onboard (emp_id, dem_id, start_date, eta_of_completion, bgc_status ,onboarding_status)  values ( ? , ? , ? , ?,?,?)";

		Object[] parameters = new Object[] { onboard.getEmp_id(), onboard.getDem_id(), onboard.getStart_date(),
				onboard.getEta_of_completion(), onboard.getBgc_status().toLowerCase(),
				onboard.getOnboarding_status().toLowerCase() };

		
		
		try {
			result = jdbcTemplate.update(sql, parameters);
		}
		catch(DuplicateKeyException exception){
			throw new InvalidDataEntryException(" - Cannot Make this entry, EmployeeId: "+onboard.getEmp_id()+" and DemandId: "+onboard.getDem_id()+" entry is already present in the table - " , exception);
		}
		
		
		
		int databaseLoggingAttempts = 0;

		if (result == 1) // upon successful add operation create log
		{
			long onb_id = this.getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id()).getOnb_id();

			while (databaseLoggingAttempts < maxDatabaseLogginAttempts) {
				try {
					result = getOnboardLogService().setLog(Operation.add, onb_id , userEmail);
					// if no exception is thrown then break out of while loop
					break;
				} catch (FailedDatabaseLoggingException exception) {
					result = 0;
					//if multiple logging attempts fail, then throw the original 
					if (databaseLoggingAttempts >= maxDatabaseLogginAttempts)
						throw exception;
					
				}

				databaseLoggingAttempts++;
			}

		}
		return result;
	}

	@Transactional
	public int update(Onboard onboard , String userEmail) {
		String sql = "update onboard set emp_id = ?,dem_id = ? , start_Date = ? , eta_of_completion = ? , onboarding_status = ? , bgc_status = ? where onb_id = ?";
		Object[] parameters = new Object[] { onboard.getEmp_id(), onboard.getDem_id(), onboard.getStart_date(),
				onboard.getEta_of_completion(), onboard.getOnboarding_status().toLowerCase(),
				onboard.getBgc_status().toLowerCase(), onboard.getOnb_id() };

		int result = 0;
		
		try {
			result = jdbcTemplate.update(sql, parameters);
		}
		catch(DuplicateKeyException exception){
			throw new InvalidDataEntryException(" - Cannot Make this change, EmployeeId: "+onboard.getEmp_id()+" and DemandId: "+onboard.getDem_id()+" entry is already present in the table - " , exception);
		}
		
		
		int databaseLoggingAttempts = 0;
		
		if (result == 1)// upon successful operation create log
		{

			while (databaseLoggingAttempts < maxDatabaseLogginAttempts) {
				try {
					result = getOnboardLogService().setLog(Operation.update, onboard.getOnb_id() , userEmail);
					// if no exception is thrown then break out of while loop
					break;
				} catch (FailedDatabaseLoggingException exception) {
					result = 0;
					//if multiple logging attempts fail, then throw the original 
					if (databaseLoggingAttempts >= maxDatabaseLogginAttempts)
						throw exception;

				}

				databaseLoggingAttempts++;
			}

		}

		return result;

	}

	@Transactional
	public int delete(long onb_id , String userEmail) {
		String sql = "delete from onboard where onb_id = ?";
		int result = jdbcTemplate.update(sql, new Object[] { onb_id });

		int databaseLoggingAttempts = 0;
		if (result == 1) {
			while (databaseLoggingAttempts < maxDatabaseLogginAttempts) {
				try {
					result = getOnboardLogService().setLog(Operation.delete, onb_id , userEmail);
					// if no exception is thrown then break out of while loop
					break;
				} catch (FailedDatabaseLoggingException exception) {
					result = 0;
					//if multiple logging attempts fail, then throw the original 
					if (databaseLoggingAttempts >= maxDatabaseLogginAttempts)
						throw exception;

				}

				databaseLoggingAttempts++;
			}

		}
		return result;

	}

}
