package com.devmf.base.security.infrastructure.adapter.persistence.repository;

import com.devmf.base.security.infrastructure.adapter.persistence.entity.AuthenticationUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationUserEntityRepository extends JpaRepository<AuthenticationUserEntity, Long> {

    Optional<AuthenticationUserEntity> findByDocumentNumber(String documentNumber);

}
