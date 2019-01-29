package com.hajdu.sp.club.update;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = {"com.sp.competition.command"})
@EnableEventHandlers
@Import({ EventuateDriverConfiguration.class})
public class ClubUpdateApp {

    public static void main(String[] args) {
        SpringApplication.run(ClubUpdateApp.class, args);
    }

}
