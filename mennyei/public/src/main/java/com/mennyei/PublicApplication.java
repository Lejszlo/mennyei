package com.mennyei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mennyei.configuration.MongoConfiguration;

import io.eventuate.javaclient.spring.EnableEventHandlers;
import io.eventuate.javaclient.spring.httpstomp.EventuateHttpStompClientConfiguration;

@SpringBootApplication
@EnableEventHandlers
@EnableMongoRepositories
@Import({EventuateHttpStompClientConfiguration.class, MongoConfiguration.class})
public class PublicApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PublicApplication.class, args);
	}
}