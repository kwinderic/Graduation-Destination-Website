package com.lazysheep.graduation_destination_website.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonInformationResponseDTO implements Serializable {

    private static final Long serialVersionUID=1L;

    private Long id;

    private String studentId;

    private String password;

    private String name;

    private Integer sex;

    private String major;

    private Integer type;

    private Integer year;

    private String college;

    private Integer eduBg;


}
