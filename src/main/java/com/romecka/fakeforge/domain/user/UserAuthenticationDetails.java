package com.romecka.fakeforge.domain.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserAuthenticationDetails implements UserDetails {

    private final String emailAddress;

    private final String apiKey;

    private final List<GrantedAuthority> grantedAuthorities;

    private final Long userId;

    public UserAuthenticationDetails(User user) {
        this.emailAddress = user.emailAddress();
        this.apiKey = user.apiKey().apiKey();
        this.grantedAuthorities = List.of(new SimpleGrantedAuthority(user.role()));
        this.userId = user.id();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return apiKey;
    }

    @Override
    public String getUsername() {
        return emailAddress;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

}
