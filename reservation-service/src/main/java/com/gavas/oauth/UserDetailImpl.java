package com.gavas.oauth;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailImpl extends User{

    public UserDetailImpl(User account) {
        super (account.getUsername(),account.getPassword(),authorities(account));
    }

    private static Collection<? extends GrantedAuthority> authorities(User account) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }
}
