package com.devmf.base.shared.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Request<T> extends Response<T> {
    private T data;
    private Map<String, String> filters;
}
