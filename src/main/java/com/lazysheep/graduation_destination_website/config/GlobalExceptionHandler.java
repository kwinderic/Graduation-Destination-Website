/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the General Public License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package com.lazysheep.graduation_destination_website.config;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.lazysheep.graduation_destination_website.common.ApiException;
import com.lazysheep.graduation_destination_website.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 通用异常拦截器
 *
 * @author zhangt2333
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<R> handleException(MissingServletRequestParameterException e) {
        log.error("", e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value())
                .body(R.fail(HttpStatus.FORBIDDEN));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<R> handleException(Exception e) {
        log.error("", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(R.fail(HttpStatus.INTERNAL_SERVER_ERROR));
    }


    @ExceptionHandler(ApiException.class)
    public ResponseEntity<R> handleException(ApiException e) {
        log.warn("", Optional.ofNullable(e.getException()).orElse(e));
        return ResponseEntity.status(e.code)
                             .body(R.fail(e.code, e.message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<R> exception(MethodArgumentNotValidException e) {
        log.warn("{} {}", e.getMessage(), e.getStackTrace()[0]);
        String message = e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(";"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(R.fail(HttpStatus.BAD_REQUEST.value(), message));
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<R> handleException(HttpMessageNotReadableException e) {
        log.warn("{} {}", e.getMessage(), e.getStackTrace()[0]);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(R.fail(HttpStatus.BAD_REQUEST.value(), "参数错误"));
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<R> handleException(JsonMappingException e) {
        log.warn("{} {}", e.getMessage(), e.getStackTrace()[0]);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(R.fail(HttpStatus.BAD_REQUEST.value(), "JSON 格式有误"));
    }



}