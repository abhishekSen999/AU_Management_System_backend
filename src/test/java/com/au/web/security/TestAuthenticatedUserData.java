package com.au.web.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAuthenticatedUserData {
	
	@Autowired
	private AuthenticatedUserData authenticatedUserData;
	
	@Test	
	public void testGetEmail() 
	{
		
		String matcherString = "org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken@43445b56: Principal: Name: [111833486939176496427], Granted Authorities: [[ROLE_USER, SCOPE_https://www.googleapis.com/auth/userinfo.email, SCOPE_https://www.googleapis.com/auth/userinfo.profile, SCOPE_openid]], User Attributes: [{at_hash=c1gGvnzbuWrX6wDJTAM1Ug, sub=111833486939176496427, email_verified=true, iss=https://accounts.google.com, given_name=Abhishek, locale=en, nonce=-6R64R4n3N7_aBdBpscdV9mX1DSnlMr0EUzxdjc3QFU, picture=https://lh4.googleusercontent.com/-PMS8FEZ5_XA/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucn8znXR5KXpg3eNeUZL4dsen10-2A/s96-c/photo.jpg, aud=[189837427266-pm4ufohgp6vg6ke2r6atq207jr62t2p4.apps.googleusercontent.com], azp=189837427266-pm4ufohgp6vg6ke2r6atq207jr62t2p4.apps.googleusercontent.com, name=Abhishek Sen, hd=accoliteindia.com, exp=2020-05-30T04:33:56Z, family_name=Sen, iat=2020-05-30T03:33:56Z, email=abhishek.sen@accoliteindia.com}]; Credentials: [PROTECTED]; Authenticated: true; Details: org.springframework.security.web.authentication.WebAuthenticationDetails@0: RemoteIpAddress: 0:0:0:0:0:0:0:1; SessionId: 93DAE8D8941207CF45992417AA4C88E5; Granted Authorities: ROLE_USER, SCOPE_https://www.googleapis.com/auth/userinfo.email, SCOPE_https://www.googleapis.com/auth/userinfo.profile, SCOPE_openid";		
		assertEquals("abhishek.sen#accoliteindia.com", authenticatedUserData.emailPatternMatcher(authenticatedUserData.emailPattern , matcherString));
		
				
	}
	

}
