package com.devmf.base.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response<T> {
    private boolean success;
    private ResponseCode code;
    private T data;
    private String message;

    public Response<T> ok(boolean success, String message) {
        setSuccess(success);
        setCode(ResponseCode.OK);
        setMessage(message);
        return this;
    }

    public Response<T> ok(String message, T data) {
        setSuccess(true);
        setCode(ResponseCode.OK);
        setMessage(message);
        setData(data);
        return this;
    }

    public Response<T> ok(boolean success, T data, String message) {
        setSuccess(success);
        setCode(ResponseCode.OK);
        setMessage(message);
        setData(data);
        return this;
    }

    public Response<T> created(String message) {
        setSuccess(Boolean.TRUE);
        setCode(ResponseCode.CREATED);
        setMessage(message);
        return this;
    }

    public Response<T> created(String message, T data) {
        setSuccess(Boolean.TRUE);
        setCode(ResponseCode.CREATED);
        setMessage(message);
        setData(data);
        return this;
    }

    public Response<T> noContent(String message) {
        setSuccess(Boolean.FALSE);
        setCode(ResponseCode.NO_CONTENT);
        setMessage(message);
        return this;
    }

    public Response<T> notFound(String message) {
        setSuccess(Boolean.FALSE);
        setCode(ResponseCode.NOT_FOUND);
        setMessage(message);
        return this;
    }

    public Response<T> badRequest(String message) {
        setSuccess(Boolean.FALSE);
        setCode(ResponseCode.BAD_REQUEST);
        setMessage(message);
        return this;
    }

    public Response<T> badRequest(String message, T data) {
        setSuccess(Boolean.FALSE);
        setCode(ResponseCode.BAD_REQUEST);
        setMessage(message);
        setData(data);
        return this;
    }

    public Response<T> unauthorized(String message) {
        setSuccess(Boolean.FALSE);
        setCode(ResponseCode.UNAUTHORIZED);
        setMessage(message);
        return this;
    }

    public Response<T> forbidden(String message) {
        setSuccess(Boolean.FALSE);
        setCode(ResponseCode.FORBIDDEN);
        setMessage(message);
        return this;
    }

    public Response<T> error(String message) {
        setSuccess(Boolean.FALSE);
        setCode(ResponseCode.ERROR);
        setMessage(message);
        return this;
    }

}
