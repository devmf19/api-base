package com.devmf.base.user.infrastructure.adapter.persistence.service;

import com.devmf.base.user.application.dto.response.UserRes;
import com.devmf.base.user.application.port.out.UserRepository;
import com.devmf.base.user.infrastructure.adapter.api.dto.request.UserReq;
import com.devmf.base.user.infrastructure.adapter.persistence.entity.UserEntity;
import com.devmf.base.user.infrastructure.adapter.persistence.mapper.InfrastructureUserMapper;
import com.devmf.base.user.infrastructure.adapter.persistence.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserEntityService implements UserRepository {

    private UserEntityRepository userEntityRepository;
    private InfrastructureUserMapper infrastructureUserMapper;

    @Override
    public Optional<UserRes> saveUser(UserReq user) {
        log.debug("{}", user);
        try {
            UserEntity userEntity = infrastructureUserMapper.toUserEntity(user);
            userEntityRepository.save(userEntity);
            return Optional.of(infrastructureUserMapper.toUserRes(userEntity));
        } catch (Exception e) {
            log.error("Failed user creation {} - {}", user, e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Boolean existsUserByDocumentNumber(String documentNumber) {
        log.debug("{}", documentNumber);
        return userEntityRepository.existsByDocumentNumber(documentNumber);
    }

    @Override
    public Optional<UserRes> findUserById(Long userId) {
        log.debug("{}", userId);
        try {
            Optional<UserEntity> userEntity = userEntityRepository.findById(userId);
            Optional<UserRes> userRes = Optional.ofNullable(infrastructureUserMapper.toUserRes(userEntity.orElse(null)));
            return userRes;
        } catch (Exception e) {
            log.error("Failed user search {} - {}", userId, e.getMessage());
            return Optional.empty();
        }
    }
}
