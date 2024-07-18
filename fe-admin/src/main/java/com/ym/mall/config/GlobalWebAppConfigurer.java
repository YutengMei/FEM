package com.ym.mall.config;

import com.ym.mall.interceptor.AuthInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Auth interceptor
 */
@Configuration
public class GlobalWebAppConfigurer implements WebMvcConfigurer {

    /**
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor()).addPathPatterns("/**");
    }

    @Bean
    @ConfigurationProperties(prefix = "secure.ignored")
    public AuthInterceptor authInterceptor(){
        return new AuthInterceptor();
    }
}
