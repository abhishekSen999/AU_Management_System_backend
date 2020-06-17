package com.au.web.authorization;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public interface LoginAuthorizationInterface {
	
	
	public Map<UserDataKey,Object> getEmailAndAuthorizationLevel(String idToken);

}
