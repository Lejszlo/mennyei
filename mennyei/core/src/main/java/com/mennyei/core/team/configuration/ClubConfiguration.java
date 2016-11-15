package com.mennyei.core.team.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mennyei.core.team.domain.ClubAggregate;
import com.mennyei.core.team.infrastructure.ClubRepository;

import io.eventuate.EventuateAggregateStore;

@Configuration
public class ClubConfiguration {

//	@Bean
//	public ClubService accountService(AggregateRepository<Account, AccountCommand> accountRepository) {
//		return new ClubService(accountRepository);
//	}

	@Bean
	public ClubRepository clubRepository(EventuateAggregateStore eventStore) {
		return new ClubRepository(ClubAggregate.class, eventStore);
	}

}