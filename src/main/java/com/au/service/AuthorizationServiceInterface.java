package com.au.service;

import org.springframework.stereotype.Component;

import com.au.web.authorization.AuthorizationLevel;

@Component
public interface AuthorizationServiceInterface {
	
	public AuthorizationLevel getUserAuthorizationLevel(String email);

}
