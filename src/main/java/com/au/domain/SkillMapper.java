package com.au.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class SkillMapper implements RowMapper<Skill> {

	@Override
	public Skill mapRow(ResultSet row, int rowNum) throws SQLException {
		Skill skill = new Skill();

		skill.setSkill_id((long) row.getLong("skill_id"));
		skill.setSkill_name((String) row.getString("skill_name"));
		return skill;
	}

}
