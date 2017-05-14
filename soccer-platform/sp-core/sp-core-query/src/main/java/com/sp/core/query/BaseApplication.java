package com.sp.core.query;

import com.sp.core.query.configurations.BeanConfigurations;
import com.sp.core.query.configurations.MongoConfiguration;
import com.sp.core.query.configurations.RestConfiguration;
import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableEventHandlers
@EnableEntityLinks
@EnableMongoRepositories
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@Import({ EventuateDriverConfiguration.class, BeanConfigurations.class, RestConfiguration.class, MongoConfiguration.class})
public class BaseApplication implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOG = LoggerFactory.getLogger(BaseApplication.class);

	@Value("${spring.profiles.active}")
	protected String springProfilesActive;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		LOG.info("=======================================");
		LOG.info("App running with active profiles: {}", springProfilesActive);
		LOG.info("=======================================");
	}

	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);
	}

}