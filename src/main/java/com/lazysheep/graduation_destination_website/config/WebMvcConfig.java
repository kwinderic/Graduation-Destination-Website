package com.lazysheep.graduation_destination_website.config;

import com.lazysheep.graduation_destination_website.componet.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
@Slf4j
@Configuration
@Component
public class WebMvcConfig implements WebMvcConfigurer {


//    @Value("${web.upload-path}")
    private String mImagesPath;

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        log.info("注册拦截器");
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/login","/user/logout");
//    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:"+mImagesPath);
    }
}
