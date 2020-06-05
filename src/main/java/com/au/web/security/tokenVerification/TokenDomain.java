package com.au.web.security.tokenVerification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenDomain {
	
	  private String iss ; // "accounts.google.com",
	  private String email ;//"abhishek.sen999@gmail.com",
	  private String email_verified ;//"true",
	  
	  private String name ; // "Abhishek Sen",

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail_verified() {
		return email_verified;
	}

	public void setEmail_verified(String email_verified) {
		this.email_verified = email_verified;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	  
}
