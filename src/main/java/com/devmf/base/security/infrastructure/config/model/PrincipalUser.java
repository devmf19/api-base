package com.devmf.base.security.infrastructure.config.model;

import com.devmf.base.security.infrastructure.adapter.persistence.entity.AuthenticationUserEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@AllArgsConstructor
public class PrincipalUser implements UserDetails {

    private AuthenticationUserEntity authenticationUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authenticationUser.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return authenticationUser.getPassword();
    }

    @Override
    public String getUsername() {
        return authenticationUser.getDocumentNumber();
    }

    public boolean isAccountExpired() {
        return authenticationUser.getEnabled();
    }
}
