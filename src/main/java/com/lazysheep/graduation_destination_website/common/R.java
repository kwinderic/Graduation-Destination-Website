/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the General Public License, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package com.lazysheep.graduation_destination_website.common;


import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName R
 * @Description 反馈结果类
 * @Author zhangt2333
 * @Date 2020/2/26 11:29
 * @Version V1.0
 **/

@Getter
@ToString
public class R <T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private long timestamp;
    private Object data;

    private R() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 成功响应，但无响应数据。
     */
    public static R ok() {
        return ok(null);
    }


    /**
     * 错误响应，需要客户端处理的
     */
    public static R fail(ApiExceptionEnum em) {
        return new R()
                .setCode(em.code)
                .setMessage(em.message);
    }

    /**
     * 错误响应，需要客户端处理的
     */
    public static R fail(int code, String message) {
        return new R()
                .setCode(code)
                .setMessage(message);
    }

    /**
     * 错误响应，存在响应数据。
     */
    public static <T> R<T> fail(HttpStatus status) {
        return new R()
                .setCode(status.value())
                .setMessage(status.getReasonPhrase())
                .setData(null);
    }

    /**
     * 错误响应，存在响应数据。
     */
    public static <T> R<T> fail(T data) {
        return new R()
                .setCode(AcceptedEnum.ERROR.code)
                .setMessage(AcceptedEnum.ERROR.message)
                .setData(data);
    }


    /**
     * 异常反馈，需要服务端处理的
     */
    public static R error(ApiExceptionEnum em) {
        return fail(em);
    }

    /**
     * 异常反馈，需要服务端处理的
     */
    public static R error() {
        return new R()
                .setCode(AcceptedEnum.ERROR.code)
                .setMessage(AcceptedEnum.ERROR.message);
    }


    /**
     * 成功响应，存在响应数据。
     */
    public static <T> R<T> ok(T data) {
        return new R()
                .setCode(AcceptedEnum.OK.code)
                .setMessage(AcceptedEnum.OK.message)
                .setData(data);
    }



    public R setData(T data) {
        this.data = data;
        return this;
    }

    public R setMessage(String message) {
        this.message = message;
        return this;
    }

    public R setCode(int code) {
        this.code = code;
        return this;
    }
}