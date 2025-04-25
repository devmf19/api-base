package com.devmf.base.user.infrastructure.adapter.persistence.mapper;

import com.devmf.base.user.application.dto.response.UserRes;
import com.devmf.base.user.infrastructure.adapter.api.dto.request.UserReq;
import com.devmf.base.user.infrastructure.adapter.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface InfrastructureUserMapper {

    UserEntity toUserEntity(UserReq userReq);

    UserRes toUserRes(UserEntity userEntity);

    Optional<UserRes> toUserRes(Optional<UserEntity> userEntity);
}
