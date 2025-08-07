package com.basket.cosf.commons;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class ApiResponseEntity<T> {
    private String message;
    private T data;
    private boolean success;

    public ApiResponseEntity(String message, T data, boolean success) {
        this.message = message;
        this.data = data;
        this.success = success;
    }

}
