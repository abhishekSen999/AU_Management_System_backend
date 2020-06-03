package com.au.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestSkill {
	
	
	
	@Test
	public void testSkillHashCodeAndEqualsContractEquality()
	{
		Skill skill1 = new Skill();
		skill1.setSkill_id(1)
			.setSkill_name("java");
		
		Skill skill2 = new Skill();
		skill2.setSkill_id(1)
			.setSkill_name("java");
		
		assertEquals(skill1.hashCode(), skill2.hashCode());
		assertEquals(true, skill1.equals(skill2));
		
	}
	
	@Test
	public void testSkillHashCodeAndEqualsContractInequality()
	{
		Skill skill1 = new Skill();
		skill1.setSkill_id(1)
			.setSkill_name("java");
		
		Skill skill2 = new Skill();
		skill2.setSkill_id(2)
			.setSkill_name("java");
		
		assertNotEquals(skill1.hashCode(), skill2.hashCode());
		assertEquals(false, skill1.equals(skill2));
		
	}
	
	

}
