package com.au.repository.SecuredDatabase;

import org.springframework.stereotype.Component;

@Component
public interface AuthorizationTableInterface {
	
	public String getUserAuthorizationLevel(String email);

}
