package com.mennyei.configuration;

import org.springframework.boot.autoconfigure.session.SessionProperties.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfiguration {

	@Bean
	public com.mongodb.Mongo mongo() throws Exception {
		return new Mongo();
	}

	@Bean
	public MongoTemplate mongoTemplate() {
		MongoTemplate mongoTemplate = new MongoTemplate(mongo(), "mennyei");
		return mongoTemplate;
	}
}
