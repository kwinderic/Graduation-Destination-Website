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
public class Alumnus implements Serializable {

    private static final Long serialVersionUID=1L;

    private String name;

    private Integer year;

    private String jobs;

    private Double salary;

    private String phone;

    private String email;
}
