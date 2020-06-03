package com.au.domain;

import org.springframework.stereotype.Component;

@Component
public class Skill {

	private long skill_id;
	private String skill_name;

	public long getSkill_id() {
		return skill_id;
	}

	public Skill setSkill_id(long skill_id) {
		this.skill_id = skill_id;
		return this;
	}

	public String getSkill_name() {
		return skill_name;
	}

	public Skill setSkill_name(String skill_name) {
		this.skill_name = skill_name;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (skill_id ^ (skill_id >>> 32));
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
		Skill other = (Skill) obj;
		if (skill_id != other.skill_id)
			return false;
		
		return true;
	}
	
	
	

}
