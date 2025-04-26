package com.devmf.base.security.infrastructure.adapter.persistence.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleCacheService {

    @CacheEvict(value = "enabledRoles", allEntries = true)
    @Scheduled(cron = "0 0 0 * * *") // daily at 00:00
    public void evictEnabledRolesCache() {
        log.warn("{}", "Role cache cleaned");
    }
}
