package com.mennyei.publicweb.competition.service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mennyei.core.competition.domain.CompetitionInfo;
import com.mennyei.core.competition.domain.match.domain.Match;
import com.mennyei.core.competition.domain.season.Stage;
import com.mennyei.core.competition.events.ClubRegistered;
import com.mennyei.core.competition.events.CompetitionAdded;
import com.mennyei.core.competition.events.MatchAdded;
import com.mennyei.core.competition.events.MatchPlayed;
import com.mennyei.publicweb.club.dto.ClubQuery;
import com.mennyei.publicweb.club.infrastructure.ClubQueryMongoRepository;
import com.mennyei.publicweb.competition.dto.CompetitionQuery;
import com.mennyei.publicweb.competition.infrastructure.CompetitionMongoRepository;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

@EventSubscriber
@Component
public class CompetitionManagementWorkflow {

    @Autowired
    private CompetitionMongoRepository competitionMongoRepository;

    @Autowired
    private ClubQueryMongoRepository clubMongoRepository;
    
    @Autowired
    private CompetitionMatchService competitionMatchService;
    
    @Autowired
    private CompetitionTableService competitionTableService;

    @Autowired
    private ModelMapper modelMapper;
    
    @EventHandlerMethod
    public void create(DispatchedEvent<CompetitionAdded> dispatchedEvent) {
        CompetitionAdded event = dispatchedEvent.getEvent();
        String competitionId = dispatchedEvent.getEntityId();
        CompetitionInfo competitionInfo = event.getCompetitionInfo();
        CompetitionQuery competitionQuery = CompetitionQuery.builder().id(competitionId).build();
        modelMapper.map(competitionInfo , competitionQuery);
        competitionQuery.getStages().addAll(event.getStages());
        competitionMongoRepository.save(competitionQuery);
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
    
    @EventHandlerMethod
    public void matchAdded(DispatchedEvent<MatchAdded> dispatchedEvent) {
        MatchAdded event = dispatchedEvent.getEvent();
        String competitionId = dispatchedEvent.getEntityId();
        CompetitionQuery competitionQuery = competitionMongoRepository.findOne(competitionId);
        Optional<Stage> stage = competitionQuery.getStages().stream().filter(s -> s.getName().equals(event.getStageName())).findFirst();
        stage.get().getTurns().add(event.getTurn());
        Collections.sort(stage.get().getTurns());
        competitionMongoRepository.save(competitionQuery);
    }
    
    @EventHandlerMethod
    public void matchPlayed(DispatchedEvent<MatchPlayed> dispatchedEvent) {
    	MatchPlayed event = dispatchedEvent.getEvent();
        String competitionId = dispatchedEvent.getEntityId();
        CompetitionQuery competitionQuery = competitionMongoRepository.findOne(competitionId);
        Match match = competitionMatchService.matchPlayed(event, competitionQuery);
        competitionTableService.refreshTable(competitionQuery, match, event.getStageName());
        competitionMongoRepository.save(competitionQuery);
    }

}