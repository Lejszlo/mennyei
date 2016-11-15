package com.mennyei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.eventuate.javaclient.spring.EnableEventHandlers;
import io.eventuate.javaclient.spring.httpstomp.EventuateHttpStompClientConfiguration;

@SpringBootApplication
@EnableJpaRepositories
@EnableEventHandlers
@Import({EventuateHttpStompClientConfiguration.class})
public class PublicApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PublicApplication.class, args);
	}
}