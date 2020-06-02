package com.au.domain;


import java.util.Map;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

@Component
public class Employee {

	/*
	 * `emp_id` INT UNSIGNED NOT NULL AUTO_INCREMENT, `name` VARCHAR(30) NOT NULL,
	 * `company_email` VARCHAR(45) NOT NULL, `personal_email` VARCHAR(45) NULL,
	 * `location` VARCHAR(20) NOT NULL,
	 */

	private long emp_id;
	private String name;
	private String company_email;
	private String personal_email;
	private String location;

	public long getEmp_id() {
		return emp_id;
	}

	public Employee setEmp_id(long emp_id) {
		this.emp_id = emp_id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Employee setName(String name) {
		this.name = name;
		return this;
	}

	public String getCompany_email() {
		return company_email;
	}

	public Employee setCompany_email(String company_email) {
		this.company_email = company_email;
		return this;
	}

	public String getPersonal_email() {
		return personal_email;
	}

	public Employee setPersonal_email(String personal_email) {
		this.personal_email = personal_email;
		return this;
	}

	public String getLocation() {
		return location;
	}

	public Employee setLocation(String location) {
		this.location = location;
		return this;
	}
	
	public static Employee mapEmployee(Map row)
	{

		Employee employee = new Employee();
		
		employee.setName((String) row.get("name"));
		employee.setEmp_id((long)row.get("emp_id"));
		employee.setCompany_email((String)row.get("company_email"));
		employee.setPersonal_email((String)row.get("personal_email"));
		employee.setLocation((String)row.get("location"));
		
		
		return employee;
			
	}
	
	
	

}
