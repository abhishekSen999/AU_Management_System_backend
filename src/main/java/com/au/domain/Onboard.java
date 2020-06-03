package com.au.domain;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class Onboard {

	private long onb_id;
	private long emp_id;
	private long dem_id;
	private Date start_date;
	private Date eta_of_completion;
	private String onboarding_status;
	private String bgc_status;

	@Override
	public String toString() {
		return "Onboard [onb_id=" + onb_id + ", emp_id=" + emp_id + ", dem_id=" + dem_id + ", start_date=" + start_date
				+ ", eta_of_completion=" + eta_of_completion + ", onboarding_status=" + onboarding_status
				+ ", bgc_status=" + bgc_status + "]";
	}

	public String getOnboarding_status() {
		return onboarding_status;
	}

	public void setOnboarding_status(String onboarding_status) {
		this.onboarding_status = onboarding_status;
	}

	public String getBgc_status() {
		return bgc_status;
	}

	public void setBgc_status(String bgc_status) {
		this.bgc_status = bgc_status;
	}

	public long getOnb_id() {
		return onb_id;
	}

	public void setOnb_id(long onb_id) {
		this.onb_id = onb_id;
	}

	public long getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(long emp_id) {
		this.emp_id = emp_id;
	}

	public long getDem_id() {
		return dem_id;
	}

	public void setDem_id(long dem_id) {
		this.dem_id = dem_id;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEta_of_completion() {
		return eta_of_completion;
	}

	public void setEta_of_completion(Date eta_of_completion) {
		this.eta_of_completion = eta_of_completion;
	}

	

}
