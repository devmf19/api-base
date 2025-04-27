package com.devmf.base.user.application.port.in;

import com.devmf.base.common.model.Response;
import com.devmf.base.user.application.dto.response.UserRes;
import com.devmf.base.user.infrastructure.adapter.api.dto.request.UserReq;

public interface UserManager {

    /**
     * Create a new user
     *
     * @param user User information
     * @return Response with creation details
     */
    Response<UserRes> createUser(UserReq user);

    /**
     * Get user by userId
     *
     * @param userId Unique user identification userId in db
     * @return Response con user information
     */
    Response<UserRes> getUserById(Long userId);

}
