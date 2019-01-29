package com.hajdu.sp.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableDiscoveryClient
@EnableEurekaServer
@SpringBootApplication(scanBasePackages = {"com.hajdu.sp.discovery"})
public class DiscoveryApp {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryApp.class, args);
    }

}
