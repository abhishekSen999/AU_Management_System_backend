package com.au.domain;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class Demand {

	private long dem_id;
	private String loction;
	private int experience_requirement;
	private int number_people;
	private Date creation_date;
	private long h_id;

	public long getDem_id() {
		return dem_id;
	}

	public Demand setDem_id(long dem_id) {
		this.dem_id = dem_id;
		return this;
	}

	public String getLoction() {
		return loction;
	}

	public Demand setLoction(String loction) {
		this.loction = loction;
		return this;
	}

	public int getExperience_requirement() {
		return experience_requirement;
	}

	public Demand setExperience_requirement(int experience_requirement) {
		this.experience_requirement = experience_requirement;
		return this;
	}

	public int getNumber_people() {
		return number_people;
	}

	public Demand setNumber_people(int number_people) {
		this.number_people = number_people;
		return this;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public Demand setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
		return this;
	}

	public long getH_id() {
		return h_id;
	}

	public Demand setH_id(long h_id) {
		this.h_id = h_id;
		return this;
	}

}
