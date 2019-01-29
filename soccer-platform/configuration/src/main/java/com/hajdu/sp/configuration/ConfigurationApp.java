package com.hajdu.sp.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication(scanBasePackages = {"com.hajdu.sp.configuration"})
public class ConfigurationApp {

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationApp.class, args);
    }
}
