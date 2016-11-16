package com.mennyei.publicweb.competition.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.mennyei.core.competition.domain.Competition;
import com.mennyei.core.competition.events.ClubRegistered;
import com.mennyei.core.competition.events.CompetationAdded;
import com.mennyei.publicweb.competition.dto.CompetitionClubListQuery;
import com.mennyei.publicweb.competition.infrastructure.CompetitionMongoRepository;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

@EventSubscriber
public class ClubsQueryWorkflow {
	
	@Autowired
	private CompetitionMongoRepository competitionMongoRepository;

	@EventHandlerMethod
	public void create(DispatchedEvent<CompetationAdded> dispatchedEvent) {
		CompetationAdded event = dispatchedEvent.getEvent();
		String competitionId = dispatchedEvent.getEntityId();
		Competition competition = event.getCompetition();
		CompetitionClubListQuery competitionListQuery = CompetitionClubListQuery.builder()
																.id(Long.valueOf(competitionId))
																.name(competition.getName())
																.build();
		competitionMongoRepository.save(competitionListQuery);
	}
	
	@EventHandlerMethod
	public CompetitionClubListQuery registerClubs(DispatchedEvent<ClubRegistered> dispatchedEvent) {
		ClubRegistered event = dispatchedEvent.getEvent();
		String competitionId = dispatchedEvent.getEntityId();
		Set<String> clubIds = event.getClubIds();
		CompetitionClubListQuery competitionClubList = competitionMongoRepository.findOne(Long.valueOf(competitionId));
		competitionClubList.getClubIds().addAll(clubIds);
		return competitionClubList;
	}

}