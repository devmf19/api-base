package com.devmf.base.common.model;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Request<T> extends Response<T> {
    @Valid
    private T data;
    private Map<String, String> filters;
}
