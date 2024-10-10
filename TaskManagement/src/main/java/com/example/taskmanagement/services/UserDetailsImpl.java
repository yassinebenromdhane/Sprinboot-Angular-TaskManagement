package com.example.taskmanagement.services;

import com.example.taskmanagement.entities.User;
import com.example.taskmanagement.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class UserDetailsImpl implements UserDetails {

    private  User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> roleAuthorities = new HashSet<>(Set.of(new SimpleGrantedAuthority(user.getRole().getName())));

        Set<GrantedAuthority> privilegeAuthorities = user.getRole().getPrivileges()
                .stream()
                .map(privilege -> new SimpleGrantedAuthority(privilege.getName()))
                .collect(Collectors.toSet());

        roleAuthorities.addAll(privilegeAuthorities);

        return roleAuthorities;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
}
