package com.gavas.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserDetailsImpl extends org.springframework.security.core.userdetails.User {

    public UserDetailsImpl(com.gavas.domain.User account) {
        super (account.getUsername(),account.getSnsId(),authorities(account));
    }

    private static Collection<? extends GrantedAuthority> authorities(com.gavas.domain.User account) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authorities;
    }
}
