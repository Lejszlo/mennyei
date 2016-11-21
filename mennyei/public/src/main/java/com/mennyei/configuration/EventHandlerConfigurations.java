package com.mennyei.configuration;

import com.mennyei.publicweb.competition.service.CompetitionManagementWorkflow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventHandlerConfigurations {
	
	@Bean
	public CompetitionManagementWorkflow clubsQueryWorkflow() {
		return new CompetitionManagementWorkflow();
	}
	
}
