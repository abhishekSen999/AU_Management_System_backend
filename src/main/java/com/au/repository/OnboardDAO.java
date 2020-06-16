package com.au.repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.au.customExceptions.FailedDatabaseLoggingException;
import com.au.domain.Onboard;
import com.au.domain.OnboardMapper;
import com.au.domain.Operation;
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
		return onboardList;
	}

	// todo add rest of the layers
	public List<Onboard> getByStartDate(Date start_date) {
		String sql = "select * from onboard where start_date=?";
		List<Onboard> onboardList = jdbcTemplate.query(sql, new Object[] { start_date }, onboardMapper);
		return onboardList;
	}

	public List<Onboard> getByEtaOfCompletion(Date eta_of_completion) {
		String sql = "select * from onboard where eta_of_completion=?";
		List<Onboard> onboardList = jdbcTemplate.query(sql, new Object[] { eta_of_completion }, onboardMapper);
		return onboardList;
	}

	public List<Onboard> getByOnboardingStatus(String onboarding_status) {
		String sql = "select * from onboard where lower(onboarding_status) = ? ";
		List<Onboard> onboardList = jdbcTemplate.query(sql, new Object[] { onboarding_status.toLowerCase() },
				onboardMapper);
		return onboardList;

	}

	public List<Onboard> getByOnboardingStatusWithWildcard(String onboarding_status) {
		String sql = "select * from onboard where lower(onboarding_status) like ? ";
		List<Onboard> onboardList = jdbcTemplate.query(sql, new Object[] { onboarding_status.toLowerCase() },
				onboardMapper);
		return onboardList;

	}

	public List<Onboard> getByBgcStatus(String bgc_status) {
		String sql = "select * from onboard where lower(bgc_status) = ?";
		List<Onboard> onboardList = jdbcTemplate.query(sql, new Object[] { bgc_status.toLowerCase() }, onboardMapper);
		return onboardList;

	}

	public List<Onboard> getByBgcStatusWithWildcard(String bgc_status) {
		String sql = "select * from onboard where lower(bgc_status) like ?";
		List<Onboard> onboardList = jdbcTemplate.query(sql, new Object[] { bgc_status.toLowerCase() }, onboardMapper);
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
		Onboard onboard = jdbcTemplate.queryForObject(sql, new Object[] { onb_id }, onboardMapper);

		return onboard;
	}

	public Onboard getByEmployeeIdAndDemandId(long emp_id, long dem_id) {

		String sql = "select * from onboard where emp_id=? and dem_id =?";
		Onboard onboard = jdbcTemplate.queryForObject(sql, new Object[] { emp_id, dem_id }, onboardMapper);

		return onboard;
	}
	

	
	
	
	@Transactional
	public int add(Onboard onboard) {

		String sql = "insert into onboard (emp_id, dem_id, start_date, eta_of_completion, bgc_status ,onboarding_status)  values ( ? , ? , ? , ?,?,?)";

		Object[] parameters = new Object[] { onboard.getEmp_id(), onboard.getDem_id(), onboard.getStart_date(),
				onboard.getEta_of_completion(), onboard.getBgc_status().toLowerCase(),
				onboard.getOnboarding_status().toLowerCase() };

		int result = jdbcTemplate.update(sql, parameters);

		int databaseLoggingAttempts = 0;

		if (result == 1) // upon successful add operation create log
		{
			long onb_id = this.getByEmployeeIdAndDemandId(onboard.getEmp_id(), onboard.getDem_id()).getOnb_id();

			while (databaseLoggingAttempts < maxDatabaseLogginAttempts) {
				try {
					result = getOnboardLogService().setLog(Operation.add, onb_id);
					// if no exception is thrown then break out of while loop
					break;
				} catch (FailedDatabaseLoggingException e) {
					result = 0;
					//if multiple logging attempts fail, then throw the original 
					if (databaseLoggingAttempts >= maxDatabaseLogginAttempts)
						throw e;
					
				}

				databaseLoggingAttempts++;
			}

		}
		return result;
	}

	@Transactional
	public int update(Onboard onboard) {
		String sql = "update onboard set emp_id = ?,dem_id = ? , start_Date = ? , eta_of_completion = ? , onboarding_status = ? , bgc_status = ? where onb_id = ?";
		Object[] parameters = new Object[] { onboard.getEmp_id(), onboard.getDem_id(), onboard.getStart_date(),
				onboard.getEta_of_completion(), onboard.getOnboarding_status().toLowerCase(),
				onboard.getBgc_status().toLowerCase(), onboard.getOnb_id() };

		int result = jdbcTemplate.update(sql, parameters);

		int databaseLoggingAttempts = 0;
		if (result == 1)// upon successful operation create log
		{

			while (databaseLoggingAttempts < maxDatabaseLogginAttempts) {
				try {
					result = getOnboardLogService().setLog(Operation.update, onboard.getOnb_id());
					// if no exception is thrown then break out of while loop
					break;
				} catch (FailedDatabaseLoggingException e) {
					result = 0;
					//if multiple logging attempts fail, then throw the original 
					if (databaseLoggingAttempts >= maxDatabaseLogginAttempts)
						throw e;

				}

				databaseLoggingAttempts++;
			}

		}

		return result;

	}

	@Transactional
	public int delete(long onb_id) {
		String sql = "delete from onboard where onb_id = ?";
		int result = jdbcTemplate.update(sql, new Object[] { onb_id });

		int databaseLoggingAttempts = 0;
		if (result == 1) {
			while (databaseLoggingAttempts < maxDatabaseLogginAttempts) {
				try {
					result = getOnboardLogService().setLog(Operation.delete, onb_id);
					// if no exception is thrown then break out of while loop
					break;
				} catch (FailedDatabaseLoggingException e) {
					result = 0;
					//if multiple logging attempts fail, then throw the original 
					if (databaseLoggingAttempts >= maxDatabaseLogginAttempts)
						throw e;

				}

				databaseLoggingAttempts++;
			}

		}
		return result;

	}

}
