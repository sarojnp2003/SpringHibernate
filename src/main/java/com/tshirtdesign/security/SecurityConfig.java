/**
 * 
 */
package com.tshirtdesign.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author saroj-gautam
 *
 */
@Configuration
@ComponentScan("com.tshirtdesign")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/*@Autowired
	private TshirtDesignUserDetailService tshirtDesignUserDetailService;*/
	
	@Autowired
	private TshirtDesignAuthenticationProvider authProvider;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("saroj").password("saroj").roles("ADMIN");
		//auth.userDetailsService(tshirtDesignUserDetailService);
		auth.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/user/admin**").access("hasRole('ROLE_ADMIN')")
			.and()
			.formLogin();

		http.csrf().disable();
	}
}