package com.devmf.base.security.infrastructure.adapter.persistence.service;

import com.devmf.base.security.infrastructure.adapter.persistence.repository.AuthenticationUserEntityRepository;
import com.devmf.base.security.infrastructure.config.model.PrincipalUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final String NO_ENCODING = "{noop}";
    private final String ADMIN1 = "ADMIN1";

    private final AuthenticationUserEntityRepository authenticationUserEntityRepository;

    @Value("${api.security.default-user}")
    private String userName;

    @Value("${api.security.default-password}")
    private String password;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("{}", username);

        return authenticationUserEntityRepository
                .findByDocumentNumber(username)
                .map(PrincipalUser::new)
                .orElseThrow(() -> new NullPointerException("Usuario no encontrado {}".concat(username)));
    }

}
