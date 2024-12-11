package com.franc.global.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.franc.global.error.BizException;
import com.franc.global.error.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

/**
 * 공통 응답 객체
 */
public record ApiResponse<T>(
        @JsonIgnore
        HttpStatus httpStatus,
        boolean success,
        @Nullable T data,
        @Nullable ExceptionDto error
) {

    public static <T> ApiResponse<T> ok(@Nullable final T data) {
        return new ApiResponse<>(HttpStatus.OK, true, data, null);
    }

    public static <T> ApiResponse<T> created(@Nullable final T data) {
        return new ApiResponse<>(HttpStatus.CREATED, true, data, null);
    }

    public static <T> ApiResponse<T> fail(final BizException e) {
        return new ApiResponse<>(e.getErrorCode().getHttpStatus(), false, null, ExceptionDto.of(e.getErrorCode()));
    }
}

