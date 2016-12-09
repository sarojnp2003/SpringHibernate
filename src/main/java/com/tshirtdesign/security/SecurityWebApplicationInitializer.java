/**
 * 
 */
package com.tshirtdesign.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * @author saroj-gautam
 *
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
	public SecurityWebApplicationInitializer() {
		super(SecurityConfig.class);
	}
}
