package com.example.blps_lab1.security;

import com.example.blps_lab1.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CookUserDetails implements UserDetails {

    private String login;

    private String password;

    private String email;


    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public CookUserDetails(String login, String password, String email, Collection<? extends GrantedAuthority> authorities) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    public static CookUserDetails build(User user) {

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getName().name()))
                .collect(Collectors.toList());

        return new CookUserDetails(user.getLogin(), user.getPassword(), user.getEmail(), authorities);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
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
        return true;
    }


    public String getEmail() {
        return email;
    }


}
