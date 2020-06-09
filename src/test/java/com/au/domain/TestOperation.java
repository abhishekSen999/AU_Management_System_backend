package com.au.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestOperation {

	@Test
	public void testAddOperation()
	{
		assertEquals(Operation.add, Operation.add);
	}
	
	
	@Test
	public void testUpdateOperation()
	{
		assertEquals(Operation.update, Operation.update);
	}
	
	@Test
	public void testDeleteOperation()
	{
		assertEquals(Operation.delete, Operation.delete);
	}
	
	
	
}
