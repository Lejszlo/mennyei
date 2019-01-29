package com.hajdu.sp.competition.query;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@EnableEventHandlers
@EnableEntityLinks
@EnableMongoRepositories
@EnableConfigurationProperties
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
@Import({ EventuateDriverConfiguration.class})
public class CompetitionQueryApp implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(CompetitionQueryApp.class);

    @Value("${spring.profiles.active}")
    protected String springProfilesActive;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOG.info("=======================================");
        LOG.info("OrganizerQueryApp running with active profiles: {}", springProfilesActive);
        LOG.info("=======================================");
    }

    public static void main(String[] args) {
        SpringApplication.run(CompetitionQueryApp.class, args);
    }

}
