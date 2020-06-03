package com.au.domain;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class ProjectAllocation {

	private long emp_id;
	private long proj_id;
	private Date start_date;
	private Date end_date;

	public long getEmp_id() {
		return emp_id;
	}

	public ProjectAllocation setEmp_id(long emp_id) {
		this.emp_id = emp_id;
		return this;
	}

	public long getProj_id() {
		return proj_id;
	}

	public ProjectAllocation setProj_id(long proj_id) {
		this.proj_id = proj_id;
		return this;
	}

	public Date getStart_date() {
		return start_date;
	}

	public ProjectAllocation setStart_date(Date start_date) {
		this.start_date = start_date;
		return this;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public ProjectAllocation setEnd_date(Date end_date) {
		this.end_date = end_date;
		return this;
	}

}
