package com.lazysheep.graduation_destination_website;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication
@ServletComponentScan
public class GraduationDestinationWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduationDestinationWebsiteApplication.class, args);
    }

}
