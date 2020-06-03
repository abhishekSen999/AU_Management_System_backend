package com.au.web.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.*;


@Configuration
@EnableWebSecurity
public class SecuirityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
		.csrf().disable()
		.cors().and()
		.antMatcher("/").authorizeRequests()
		.antMatchers("/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.oauth2Login();
//		
	}		
	
}
		
		
		
//			.sessionManagement()
//			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		httpSecurity.authorizeRequests().anyRequest().hasRole("USER");
		
			
			
			
//			(SessionCreationPolicy.STATELESS);
//        http.authorizeRequests().anyRequest().hasRole("USER");
		

//		}
//	 @Override
//	    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//	        resources.resourceId(oAuthProperties.getClientId());
//	    }
//	}
//	 
//		@Override
//	    public void configure(WebSecurityConfigurer<SecurityBuilder<Filter>> resources) throws Exception {
//	        resources.resourceId(oAuthProperties.getClientId());
//	
//	
                
	
	

