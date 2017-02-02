package com.mennyei;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
class RestConfiguration extends RepositoryRestMvcConfiguration {

    @Override
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return super.objectMapper();
    }
}