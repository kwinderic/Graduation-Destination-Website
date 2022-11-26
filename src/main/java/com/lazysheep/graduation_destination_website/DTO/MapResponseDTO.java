package com.lazysheep.graduation_destination_website.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MapResponseDTO implements Serializable {

    private static final Long serialVersionUID=1L;

    private String companyName;

    private Integer number=0;

    private String description="好公司";

    private String link="www.xxx.com";

    private List<Alumnus> alumnus;
}
