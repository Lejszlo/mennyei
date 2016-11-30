package com.mennyei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mennyei.configurations.BeanConfigurations;

import io.eventuate.javaclient.spring.EnableEventHandlers;
import io.eventuate.javaclient.spring.jdbc.EventuateJdbcEventStoreConfiguration;

@SpringBootApplication
@EnableEventHandlers
@EnableMongoRepositories
@Import({EventuateJdbcEventStoreConfiguration.class, BeanConfigurations.class})
public class PublicApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PublicApplication.class, args);
	}
	
}