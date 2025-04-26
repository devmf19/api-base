package com.devmf.base.security.infrastructure.config.manager;

import com.devmf.base.security.infrastructure.adapter.persistence.entity.AuthenticationUserEntity;

import java.util.Optional;

public interface AuthorizationUserDetailsManager {

    Optional<AuthenticationUserEntity> findByDocumentNumber(String documentNumber);

}
