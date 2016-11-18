package com.mennyei.publicweb.competition.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.eventuate.javaclient.spring.EnableEventHandlers;

@EnableEventHandlers
@Configuration
public class CompetitionQueryService {

	  @Bean
	  public ClubsQueryWorkflow orderHistoryViewWorkflow() {
	    return new ClubsQueryWorkflow();
	  }
	
}
