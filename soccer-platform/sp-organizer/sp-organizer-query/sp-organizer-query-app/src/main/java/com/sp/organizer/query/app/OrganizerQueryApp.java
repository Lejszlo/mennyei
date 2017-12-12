package com.sp.organizer.query.app;

import com.sp.core.query.configurations.BeanConfigurations;
import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication(scanBasePackages = {"com.sp.organizer.query.app"})
@EnableEventHandlers
@EnableEntityLinks
@EnableMongoRepositories(basePackages = {"com.sp.organizer.query.app"})
@EnableConfigurationProperties
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@Import({ EventuateDriverConfiguration.class, BeanConfigurations.class, MongoConfiguration.class})
public class OrganizerQueryApp implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(OrganizerQueryApp.class);

    @Value("${spring.profiles.active}")
    protected String springProfilesActive;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOG.info("=======================================");
        LOG.info("OrganizerQueryApp running with active profiles: {}", springProfilesActive);
        LOG.info("=======================================");
    }

    public static void main(String[] args) {
        SpringApplication.run(OrganizerQueryApp.class, args);
    }

}
