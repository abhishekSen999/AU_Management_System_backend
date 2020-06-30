package com.au.domain;

import java.sql.Date;
import java.util.Objects;

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Onboard onboard = (Onboard) o;
		return onb_id == onboard.onb_id &&
				emp_id == onboard.emp_id &&
				dem_id == onboard.dem_id &&
				Objects.equals(start_date, onboard.start_date) &&
				Objects.equals(eta_of_completion, onboard.eta_of_completion) &&
				Objects.equals(onboarding_status, onboard.onboarding_status) &&
				Objects.equals(bgc_status, onboard.bgc_status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(onb_id, emp_id, dem_id, start_date, eta_of_completion, onboarding_status, bgc_status);
	}

	@Override
	public String toString() {
		return "Onboard [onb_id=" + onb_id + ", emp_id=" + emp_id + ", dem_id=" + dem_id + ", start_date=" + start_date
				+ ", eta_of_completion=" + eta_of_completion + ", onboarding_status=" + onboarding_status
				+ ", bgc_status=" + bgc_status + "]"; 
	}

	public String getOnboarding_status() {
		return onboarding_status;
	}

	public Onboard setOnboarding_status(String onboarding_status) {
		this.onboarding_status = onboarding_status;
		return this;
	}

	public String getBgc_status() {
		return bgc_status;
	}

	public Onboard setBgc_status(String bgc_status) {
		this.bgc_status = bgc_status;
		return this;
	}

	public long getOnb_id() {
		return onb_id;
	}

	public Onboard setOnb_id(long onb_id) {
		this.onb_id = onb_id;
		return this;
	}

	public long getEmp_id() {
		return emp_id;
	}

	public Onboard setEmp_id(long emp_id) {
		this.emp_id = emp_id;
		return this;
	}

	public long getDem_id() {
		return dem_id;
	}

	public Onboard setDem_id(long dem_id) {
		this.dem_id = dem_id;
		return this;
	}

	public Date getStart_date() {
		return start_date;
	}

	public Onboard setStart_date(Date start_date) {
		this.start_date = start_date;
		return this;
	}

	public Date getEta_of_completion() {
		return eta_of_completion;
	}

	public Onboard setEta_of_completion(Date eta_of_completion) {
		this.eta_of_completion = eta_of_completion;
		return this;
	}

	

}
