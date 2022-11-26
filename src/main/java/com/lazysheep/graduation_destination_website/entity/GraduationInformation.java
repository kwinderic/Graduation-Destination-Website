package com.lazysheep.graduation_destination_website.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class GraduationInformation implements Serializable {

    private static final Long serialVersionUID=1L;

    private Long id;

    private String studentId;

    private String jobs;

    private String organization;

    private String place;

    private Double salary;

    private Integer type;

    private Integer status;

}
