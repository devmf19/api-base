package com.devmf.base.security.infrastructure.adapter.persistence.repository;

import com.devmf.base.security.infrastructure.adapter.persistence.entity.RoleEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {

    @Cacheable("enabledRoles")
    List<RoleEntity> findByEnabledTrue();

}
