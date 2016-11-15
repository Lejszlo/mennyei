package com.mennyei.core.competition.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mennyei.core.competition.domain.CompetitionAggregator;
import com.mennyei.core.competition.infrastructure.CompetitionRepository;

import io.eventuate.EventuateAggregateStore;

@Configuration
public class CompetitionConfiguration {

	@Bean
	public CompetitionRepository competitionRepository(EventuateAggregateStore eventStore) {
		return new CompetitionRepository(CompetitionAggregator.class, eventStore);
	}
	
}
