package com.lazysheep.graduation_destination_website.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ContactInformation implements Serializable {

    private static final Long serialVersionUID=1L;

    private Long id;

    private String studentId;

    private String email;

    private String phone;

    private String qq;

    private String wechat;

    private Integer status;
}
