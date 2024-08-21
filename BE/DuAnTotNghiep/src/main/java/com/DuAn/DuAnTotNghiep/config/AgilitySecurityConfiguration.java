package com.DuAn.DuAnTotNghiep.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AgilitySecurityConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .maxAge(3600)
                .allowedMethods(HttpMethod.OPTIONS.name(), HttpMethod.POST.name(), HttpMethod.PATCH.name(), HttpMethod.HEAD.name(), HttpMethod.GET.name(), HttpMethod.PUT.name(),
                        HttpMethod.TRACE.name(), HttpMethod.DELETE.name())
                .allowedHeaders(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE, HttpHeaders.CONTENT_DISPOSITION).exposedHeaders(HttpHeaders.CONTENT_DISPOSITION);
    }
}
