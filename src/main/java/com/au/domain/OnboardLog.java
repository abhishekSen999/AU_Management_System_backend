package com.au.domain;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class OnboardLog {

	private long log_id;
	private Timestamp timestamp;
	private String operator;
	private Operation operation;
	private long onb_id;
	private long emp_id;
	private long dem_id;
	private Date start_date;
	private Date eta_of_completion;
	private String onboarding_status;
	private String bgc_status;

	public long getLog_id() {
		return log_id;
	}

	public OnboardLog setLog_id(long log_id) {
		this.log_id = log_id;
		return this;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public OnboardLog setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public String getOperator() {
		return operator;
	}

	public OnboardLog setOperator(String operator) {
		this.operator = operator;
		return this;
	}

	public Operation getOperation() {
		return operation;
	}

	public OnboardLog setOperation(Operation operation) {
		this.operation = operation;
		return this;
	}

	public long getOnb_id() {
		return onb_id;
	}

	public OnboardLog setOnb_id(long onb_id) {
		this.onb_id = onb_id;
		return this;
	}

	public long getEmp_id() {
		return emp_id;
	}

	public OnboardLog setEmp_id(long emp_id) {
		this.emp_id = emp_id;
		return this;
	}

	public long getDem_id() {
		return dem_id;
	}

	public OnboardLog setDem_id(long dem_id) {
		this.dem_id = dem_id;
		return this;
	}

	public Date getStart_date() {
		return start_date;
	}

	public OnboardLog setStart_date(Date start_date) {
		this.start_date = start_date;
		return this;
	}

	public Date getEta_of_completion() {
		return eta_of_completion;
	}

	public OnboardLog setEta_of_completion(Date eta_of_completion) {
		this.eta_of_completion = eta_of_completion;
		return this;
	}

	public String getOnboarding_status() {
		return onboarding_status;
	}

	public OnboardLog setOnboarding_status(String onboarding_status) {
		this.onboarding_status = onboarding_status;
		return this;
	}

	public String getBgc_status() {
		return bgc_status;
	}

	public OnboardLog setBgc_status(String bgc_status) {
		this.bgc_status = bgc_status;
		return this;
	}

	@Override
	public String toString() {
		return "OnboardLog [log_id=" + log_id + ", timestamp=" + timestamp + ", operator=" + operator + ", operation="
				+ operation + ", onb_id=" + onb_id + ", emp_id=" + emp_id + ", dem_id=" + dem_id + ", start_date="
				+ start_date + ", eta_of_completion=" + eta_of_completion + ", onboarding_status=" + onboarding_status
				+ ", bgc_status=" + bgc_status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bgc_status == null) ? 0 : bgc_status.hashCode());
		result = prime * result + (int) (dem_id ^ (dem_id >>> 32));
		result = prime * result + (int) (emp_id ^ (emp_id >>> 32));
		result = prime * result + ((eta_of_completion == null) ? 0 : eta_of_completion.hashCode());
		result = prime * result + (int) (log_id ^ (log_id >>> 32));
		result = prime * result + (int) (onb_id ^ (onb_id >>> 32));
		result = prime * result + ((onboarding_status == null) ? 0 : onboarding_status.hashCode());
		result = prime * result + ((operation == null) ? 0 : operation.hashCode());
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + ((start_date == null) ? 0 : start_date.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OnboardLog other = (OnboardLog) obj;
		if (bgc_status == null) {
			if (other.bgc_status != null)
				return false;
		} else if (!bgc_status.equals(other.bgc_status))
			return false;
		if (dem_id != other.dem_id)
			return false;
		if (emp_id != other.emp_id)
			return false;
		if (eta_of_completion == null) {
			if (other.eta_of_completion != null)
				return false;
		} else if (!eta_of_completion.equals(other.eta_of_completion))
			return false;
		if (log_id != other.log_id)
			return false;
		if (onb_id != other.onb_id)
			return false;
		if (onboarding_status == null) {
			if (other.onboarding_status != null)
				return false;
		} else if (!onboarding_status.equals(other.onboarding_status))
			return false;
		if (operation != other.operation)
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (start_date == null) {
			if (other.start_date != null)
				return false;
		} else if (!start_date.equals(other.start_date))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}
	
	
	
	
	

	

}
