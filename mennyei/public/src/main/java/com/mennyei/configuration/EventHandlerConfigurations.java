package com.mennyei.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mennyei.publicweb.competition.service.ClubsQueryWorkflow;

@Configuration
public class EventHandlerConfigurations {
	
	@Bean
	public ClubsQueryWorkflow clubsQueryWorkflow() {
		return new ClubsQueryWorkflow();
	}
	
}
