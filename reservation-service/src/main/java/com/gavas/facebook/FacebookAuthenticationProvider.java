package com.gavas.facebook;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.HashSet;

public class FacebookAuthenticationProvider implements AuthenticationProvider {
    private FacebookService facebookService;
    private String facebookRole = "ROLE_FACEBOOK";

    public void setFacebookService(FacebookService facebookService) {
        this.facebookService = facebookService;
    }
    
	public void setFacebookRole(String facebookRole) {
		this.facebookRole = facebookRole;
	}



	protected Collection<GrantedAuthority> generateAuthorities() {
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(facebookRole));
		return authorities;
	}

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		System.out.println("gogo");
		Assert.isInstanceOf(FacebookAuthenticationToken.class, authentication, "Only FacebookAuthenticationProvider is supported");
		Collection<GrantedAuthority> authorities = generateAuthorities();
		FacebookUser facebookUser = facebookService.findUser();
		FacebookAuthenticationToken token = new FacebookAuthenticationToken(facebookUser.getName(), null, authorities);
		token.setDetails(facebookUser);
		System.out.println(facebookUser.getUsername());
		return token;
	}

	@Override
    public boolean supports(Class<?> authentication) {
        return (FacebookAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
