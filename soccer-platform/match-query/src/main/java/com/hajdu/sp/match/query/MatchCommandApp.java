package com.hajdu.sp.match.query;

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

@SpringBootApplication(scanBasePackages = "com.sp.match.command")
@EnableEventHandlers
@Import({ EventuateDriverConfiguration.class})
public class MatchCommandApp implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(MatchCommandApp.class);

    @Value("${spring.profiles.active}")
    protected String springProfilesActive;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOG.info("=======================================");
        LOG.info("MatchCommandApp running with active profiles: {}", springProfilesActive);
        LOG.info("=======================================");
    }

    public static void main(String[] args) {
        SpringApplication.run(MatchCommandApp.class, args);
    }

}
