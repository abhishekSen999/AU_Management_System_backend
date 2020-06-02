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

}
