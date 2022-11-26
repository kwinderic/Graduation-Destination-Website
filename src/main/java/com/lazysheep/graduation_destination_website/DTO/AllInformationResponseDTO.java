package com.lazysheep.graduation_destination_website.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllInformationResponseDTO implements Serializable {

    private static final Long serialVersionUID=1L;

    private String studentId;

    private String password;

    private String name;

    private Integer sex;

    private String major;

    private Integer accountType;

    private Integer year;

    private String college;

    private Integer eduBg;

    private String email;

    private String phone;

    private String qq;

    private String wechat;

    private String jobs;

    private String organization;

    private String place;

    private Double salary;

    private Integer destinationType;
}
