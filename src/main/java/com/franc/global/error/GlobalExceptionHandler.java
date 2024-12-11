package com.franc.global.error;

import com.franc.global.common.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 요청 값 유효성 검증에 대한 예외 - @Valid
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ApiResponse<?> handleMethodArgmentNotValidExceptionHandler(MethodArgumentNotValidException e, BindingResult valid) {
        log.error("GlobalExceptionHandler catch MethodArgmentNotValidExceptionHandler : {}", valid.getFieldError().getDefaultMessage());
        return ApiResponse.fail(new BizException(ErrorCode.REQUEST_ARGUMENT_NOT_VALID));
    }

    // 요청 값 유효성 검증에 대한 예외 - @Validate
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ApiResponse<?> handleMethodConstraintViolationExceptionHandler(ConstraintViolationException e) {
        log.error("GlobalExceptionHandler catch MethodConstraintViolationExceptionHandler : {}", e.getMessage());
        return ApiResponse.fail(new BizException(ErrorCode.REQUEST_ARGUMENT_NOT_VALID));
    }

    // 존재하지 않는 요청에 대한 예외
    @ExceptionHandler(value = {NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
    public ApiResponse<?> handleNoPageFoundException(Exception e) {
        log.error("GlobalExceptionHandler catch NoHandlerFoundException : {}", e.getMessage());
        return ApiResponse.fail(new BizException(ErrorCode.NOT_FOUND_END_POINT));
    }


    // 커스텀 예외
    @ExceptionHandler(value = {BizException.class})
    public ApiResponse<?> handleBizException(BizException e) {
        log.error("handleBizException() in GlobalExceptionHandler throw BizException : {}", e.getErrorCode().getMessage());
        return ApiResponse.fail(e);
    }

    // 기본 예외
    @ExceptionHandler(value = {Exception.class})
    public ApiResponse<?> handleException(Exception e) {
        log.error("handleException() in GlobalExceptionHandler throw Exception : {}", e.getMessage());
        e.printStackTrace();
        return ApiResponse.fail(new BizException(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
