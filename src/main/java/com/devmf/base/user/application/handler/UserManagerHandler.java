package com.devmf.base.user.application.handler;

import com.devmf.base.common.model.Response;
import com.devmf.base.user.application.dto.constant.UserResponseString;
import com.devmf.base.user.application.dto.response.UserRes;
import com.devmf.base.user.application.port.in.UserManager;
import com.devmf.base.user.application.port.out.UserRepository;
import com.devmf.base.user.infrastructure.adapter.api.dto.request.UserReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserManagerHandler implements UserManager {

    private final UserRepository userRepository;

    @Override
    public Response<UserRes> createUser(UserReq user) {
        /**
         * 1. User document validation
         * 2. Store user
         * 3. User creation validation
         * 4. Return created user
         */

        //Todo: Implement userReq validation

        boolean existUser = userRepository.existsUserByDocumentNumber(user.getDocumentNumber());
        if (existUser) {
            return new Response<UserRes>().badRequest(UserResponseString.USER_ALREADY_EXISTS);
        }

        Optional<UserRes> userRes = userRepository.saveUser(user);

        if(userRes.isEmpty()) {
            return new Response<UserRes>().error(UserResponseString.NOT_CREATED_USER);
        }

        return new Response<UserRes>().created(
                UserResponseString.CREATED_USER,
                userRes.get()
        );
    }

    @Override
    public Response<UserRes> getUserById(Long userId) {
        /**
         * 1. User userId validation
         * 2. Get user by userId
         * 3. User not found validation
         * 4. Return user
         */

        if(userId == null) {
            return new Response<UserRes>().badRequest(UserResponseString.ID_REQUIRED);
        }

        Optional<UserRes> userRes = userRepository.findUserById(userId);

        if(userRes.isEmpty()) {
            return new Response<UserRes>().error(UserResponseString.NOT_FOUND_USER);
        }

        return new Response<UserRes>().ok(
                UserResponseString.SUCCESS,
                userRes.get()
        );
    }
}
