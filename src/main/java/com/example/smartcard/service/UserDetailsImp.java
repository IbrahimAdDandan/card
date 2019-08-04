package com.example.smartcard.service;

import com.example.smartcard.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class UserDetailsImp implements UserDetails {

    private User user;

    public UserDetailsImp() {
    }

    public UserDetailsImp(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(this.user.getRole_name()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.user.isIsEnabled();
    }

    public Long getUserid() {
        return this.user.getId();
    }

    public String getUSerEmail() {
        return this.user.getEmail();
    }
    
    public Long getNationalId() {
        return this.user.getIdNum();
    }

}
