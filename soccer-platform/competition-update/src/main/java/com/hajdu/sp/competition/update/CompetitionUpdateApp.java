package com.hajdu.sp.competition.update;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication(scanBasePackages = {"com.hajdu.sp.competition.update"})
@EnableEventHandlers
@EnableOAuth2Client
@EnableResourceServer
@Import({EventuateDriverConfiguration.class})
public class CompetitionUpdateApp {

    public static void main(String[] args) {
        SpringApplication.run(CompetitionUpdateApp.class, args);
    }
}

