package com.sp.core.query.configurations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.*;

@Configuration
public class BeanConfigurations {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

    @Bean(name = "OBJECT_MAPPER_BEAN")
    @Primary
    public ObjectMapper jsonObjectMapper() {
        return json()
                .serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t include null values
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) //ISODate
                .modules(new JavaTimeModule())
                .build();
    }

}
