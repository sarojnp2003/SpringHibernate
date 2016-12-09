/**
 * 
 */
package com.tshirtdesign.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.tshirtdesign.model.User;
import com.tshirtdesign.service.UserService;

/**
 * @author saroj-gautam
 *
 */
@Component
public class TshirtDesignAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#
	 * authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// username and password from login form
		String userName = authentication.getPrincipal().toString().trim();
		String password = authentication.getCredentials().toString();

		User user = userService.findByUsername(userName);
		if (user == null) {
			throw new BadCredentialsException("Bad Credentials");
		}

		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("Bad Credentials");
		}

		return new UsernamePasswordAuthenticationToken(userName, password, getGrantedAuthorities(user));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#
	 * supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	/**
	 * @param user
	 * @return
	 */
	private Collection<? extends GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		return authorities;
	}
}