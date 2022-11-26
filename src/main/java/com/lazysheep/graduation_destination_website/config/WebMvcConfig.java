package com.lazysheep.graduation_destination_website.config;

import com.lazysheep.graduation_destination_website.componet.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                //.allowedHeaders("custom-header")   //允许前端携带的请求头,有自定义请求头就写进去
                .allowedHeaders("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")               //允许前端请求的方法，"*"表示所有
//                .allowCredentials(true)			   //是否允许附带身份凭证和cookies
                .maxAge(1800)					   //预见请求有效时间
                .exposedHeaders("custom-header");  //允许浏览器访问的响应头,有自定义响应头就写进去
    }
}
