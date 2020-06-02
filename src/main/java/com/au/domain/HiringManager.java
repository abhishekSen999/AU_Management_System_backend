package com.au.domain;

import org.springframework.stereotype.Component;

@Component
public class HiringManager {

	private String name;
	private String email;
	private long ph_no;
	private int country_code;
	private String team_name;
	private String dept;
	private long h_id;

	
	
	
	public long getH_id() {
		return h_id;
	}

	public HiringManager setH_id(long h_id) {
		this.h_id = h_id;
		return this;
	}

	public String getName() {
		return name;
	}

	public HiringManager setName(String name) {
		this.name = name;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public HiringManager setEmail(String email) {
		this.email = email;
		return this;
	}

	public long getPh_no() {
		return ph_no;
	}

	public HiringManager setPh_no(long ph_no) {
		this.ph_no = ph_no;
		return this;
	}

	public int getCountry_code() {
		return country_code;
	}

	public HiringManager setCountry_code(int country_code) {
		this.country_code = country_code;
		return this;
	}

	public String getTeam_name() {
		return team_name;
	}

	public HiringManager setTeam_name(String team_name) {
		this.team_name = team_name;
		return this;
	}

	public String getDept() {
		return dept;
	}

	public HiringManager setDept(String dept) {
		this.dept = dept;
		return this;
	}

}
