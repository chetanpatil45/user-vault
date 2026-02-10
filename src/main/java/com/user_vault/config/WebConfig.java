package com.user_vault.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/dashboard/**","/home","/edit","/delete","/reset","/reset-pass")
                .excludePathPatterns("/login", "/css/**", "/js/**","/signup","/forgot-pass");
    }
}
