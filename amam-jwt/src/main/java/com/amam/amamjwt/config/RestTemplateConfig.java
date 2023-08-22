package com.amam.amamjwt.config;

import com.amam.amamjwt.controller.ControllerAdvice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    @ConditionalOnMissingBean
    public ControllerAdvice controllerAdvice() {
        return new ControllerAdvice();
    }

}