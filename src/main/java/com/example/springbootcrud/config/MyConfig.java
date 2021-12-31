package com.example.springbootcrud.config;

import com.example.springbootcrud.interceptor.RedisInterceptor;
import com.example.springbootcrud.interceptor.loginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Autowired
    RedisInterceptor redisInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new loginInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/js/**","/css/**","/img/**","/login");
        registry.addInterceptor(redisInterceptor).addPathPatterns("/**").excludePathPatterns("/","/js/**","/css/**","/img/**","/login","/templates/**");
    }
}

