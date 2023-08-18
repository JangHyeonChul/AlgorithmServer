package com.algorithm.algoprojectserver;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private String userImg = "/resuources/img/user";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/user/**")
                .addResourceLocations("file:C:///Users/wkdgu/Documents/userimg/");
    }
}
