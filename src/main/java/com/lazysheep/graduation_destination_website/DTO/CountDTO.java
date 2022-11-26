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
public class CountDTO implements Serializable {

    private static final Long serialVersionUID=1L;

    private Integer value;

    private String name;
}
