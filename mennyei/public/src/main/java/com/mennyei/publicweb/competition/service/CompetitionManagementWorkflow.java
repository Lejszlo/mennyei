package com.mennyei.publicweb.competition.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mennyei.core.competition.events.ClubRegistered;
import com.mennyei.core.competition.events.CompetitionAdded;
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
    private CompetitionTableService competitionTableService;

    @Autowired
    private ModelMapper modelMapper;
    
    @EventHandlerMethod
    public void create(DispatchedEvent<CompetitionAdded> dispatchedEvent) {
        CompetitionAdded event = dispatchedEvent.getEvent();
        String competitionId = dispatchedEvent.getEntityId();
        CompetitionQuery competitionQuery = CompetitionQuery.builder().id(competitionId).build();
        modelMapper.map(event , competitionQuery);
        competitionQuery.getStages().addAll(event.getStages());
        competitionTableService.createTables(event.getStages(), competitionQuery);
        competitionMongoRepository.save(competitionQuery);
    }

    @EventHandlerMethod
    public void registerClubs(DispatchedEvent<ClubRegistered> dispatchedEvent) {
        ClubRegistered event = dispatchedEvent.getEvent();
        String competitionId = dispatchedEvent.getEntityId();
        Set<String> clubIds = event.getClubIds();
        CompetitionQuery competitionQuery = competitionMongoRepository.findOne(competitionId);
        List<ClubQuery> clubQueries = clubIds.stream().map(s -> clubMongoRepository.findOne(s)).collect(Collectors.toList());
        competitionQuery.getClubs().addAll(clubQueries);
        competitionMongoRepository.save(competitionQuery);
        competitionTableService.createTableRow(clubQueries, competitionQuery);
    }
    
}