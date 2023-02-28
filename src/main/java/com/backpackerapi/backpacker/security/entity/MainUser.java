package com.backpackerapi.backpacker.security.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementa los privilegios de cada usuario
 */
public class MainUser implements UserDetails {
    private String name;
    private String userName;
    private String email;
    private String password;
//  Es una clase exclusiva de la segirdad  GrantedAuthority` de security core
//  ? hace referencia a que es una clase generica
    private Collection<? extends GrantedAuthority> authorities;

    public MainUser(String name, String userName, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static MainUser buid(User user){
        List<GrantedAuthority> authorities =
                user.getRoles().stream().map(
                  role -> new SimpleGrantedAuthority(
                          role.getRolename().name()
                  )
                ).collect(Collectors.toList());
        return new MainUser(user.getName(), user.getUsername(), user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
// gettes
    public String getName() {
        return name;
    }

    public String getEmail(){
        return email;
    }
}
