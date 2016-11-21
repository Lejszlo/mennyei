package com.mennyei.publicweb.competition.service;

import com.mennyei.core.competition.domain.Competition;
import com.mennyei.core.competition.events.ClubRegistered;
import com.mennyei.core.competition.events.CompetitionAdded;
import com.mennyei.publicweb.club.dto.ClubQuery;
import com.mennyei.publicweb.club.infrastructure.ClubMongoRepository;
import com.mennyei.publicweb.competition.dto.CompetitionQuery;
import com.mennyei.publicweb.competition.infrastructure.CompetitionMongoRepository;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@EventSubscriber
@Component
public class CompetitionManagementWorkflow {

    @Autowired
    private CompetitionMongoRepository competitionMongoRepository;

    @Autowired
    private ClubMongoRepository clubMongoRepository;

    @EventHandlerMethod
    public void create(DispatchedEvent<CompetitionAdded> dispatchedEvent) {
        CompetitionAdded event = dispatchedEvent.getEvent();
        String competitionId = dispatchedEvent.getEntityId();
        Competition competition = event.getCompetition();
        CompetitionQuery competitionListQuery = CompetitionQuery.builder()
                .id(competitionId)
                .name(competition.getName())
                .build();
        competitionMongoRepository.save(competitionListQuery);
    }

    @EventHandlerMethod
    public void registerClubs(DispatchedEvent<ClubRegistered> dispatchedEvent) {
        ClubRegistered event = dispatchedEvent.getEvent();
        String competitionId = dispatchedEvent.getEntityId();
        Set<String> clubIds = event.getClubIds();
        CompetitionQuery competitionClubList = competitionMongoRepository.findOne(competitionId);
        Set<ClubQuery> clubQueries = clubIds.stream().map(s -> clubMongoRepository.findOne(s)).collect(Collectors.toSet());
        competitionClubList.getClubs().addAll(clubQueries);
        competitionMongoRepository.save(competitionClubList);
    }

}