package com.hajdu.sp.competition.query.handler;

import com.google.common.collect.Lists;
import com.hajdu.sp.competition.lib.event.*;
import com.hajdu.sp.competition.lib.value.season.Season;
import com.hajdu.sp.competition.lib.value.season.Stage;
import com.hajdu.sp.competition.lib.value.season.Turn;
import com.hajdu.sp.competition.query.entity.CompetitionDocument;
import com.hajdu.sp.competition.query.entity.SeasonDocument;
import com.hajdu.sp.competition.query.entity.StageDocument;
import com.hajdu.sp.competition.query.entity.TurnDocument;
import com.hajdu.sp.competition.query.repository.CompetitionQueryMongoRepository;
import com.hajdu.sp.match.lib.value.MatchId;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@EventSubscriber
@Component
public class CompetitionEventHandler {

    private final CompetitionQueryMongoRepository competitionMongoRepository;

    @Autowired
    public CompetitionEventHandler(CompetitionQueryMongoRepository competitionMongoRepository) {
        this.competitionMongoRepository = competitionMongoRepository;
    }

    @EventHandlerMethod
    public void createCompetition(DispatchedEvent<CompetitionCreated> dispatchedEvent) {
        CompetitionCreated competitionCreatedEvent = dispatchedEvent.getEvent();
        String competitionId = dispatchedEvent.getEntityId();
        CompetitionDocument competitionQuery = CompetitionDocument
                .builder(competitionCreatedEvent.getCompetitionInfo())
        		.id(competitionId)
                .seasons(Lists.newArrayList())
        		.build();
        competitionMongoRepository.save(competitionQuery);
    }

    @EventHandlerMethod
    public void addSeason(DispatchedEvent<SeasonAdded> dispatchedEvent) throws Exception {
        SeasonAdded seasonAdded = dispatchedEvent.getEvent();
        String competitionId = dispatchedEvent.getEntityId();
        CompetitionDocument competitionDocument = competitionMongoRepository.findById(competitionId).orElseThrow(Exception::new);

        SeasonDocument seasonDocument = convertSeason(seasonAdded.getSeason(), competitionDocument);
        competitionDocument.getSeasons().add(seasonDocument);

        competitionMongoRepository.save(competitionDocument);
    }

    @EventHandlerMethod
    public void addStage(DispatchedEvent<StageAdded> dispatchedEvent) throws Exception {
        StageAdded stageAddedEvent = dispatchedEvent.getEvent();
        String competitionId = dispatchedEvent.getEntityId();
        CompetitionDocument competitionDocument = competitionMongoRepository.findById(competitionId).orElseThrow(Exception::new);

        StageDocument stageDocument = convertStage(stageAddedEvent.getStage());
        competitionDocument.getSeasons()
                .stream()
                .filter(s -> s.getId().equals(stageAddedEvent.getStage().getId().getSeasonId().getSeasonUuid().toString()))
                .findFirst()
                .ifPresent(s -> s.getStages().add(stageDocument));

        competitionMongoRepository.save(competitionDocument);
    }

    @EventHandlerMethod
    public void addClubsToStage(DispatchedEvent<ClubsAdded> dispatchedEvent) throws Exception {
        ClubsAdded clubsAdded = dispatchedEvent.getEvent();
        String competitionId = dispatchedEvent.getEntityId();
        CompetitionDocument competitionDocument = competitionMongoRepository.findById(competitionId).orElseThrow(Exception::new);

        competitionDocument
                .getSeasons()
                .stream().filter(s ->s.getId().equals(clubsAdded.getSeasonId().toString()))
                .findFirst()
                .orElseThrow(Exception::new)// TODO másik exception
                .getStages().stream()
                .filter(stageDocument -> stageDocument.getId().equals(clubsAdded.getStageId().toString()))
                .findFirst()
                .ifPresent(stageDocument -> stageDocument.setClubs(clubsAdded.getClubIds()));

        competitionMongoRepository.save(competitionDocument);
    }

    @EventHandlerMethod
    public void addTurnsToStage(DispatchedEvent<TurnsAdded> dispatchedEvent) throws Exception {
        TurnsAdded turnsAdded = dispatchedEvent.getEvent();
        String competitionId = dispatchedEvent.getEntityId();
        CompetitionDocument competitionDocument = competitionMongoRepository.findById(competitionId).orElseThrow(Exception::new);

        List<TurnDocument> turnDocuments = turnsAdded
                .getTurns()
                .stream()
                .map(this::convertTurn)
                .collect(Collectors.toList());

//        competitionDocument.getStages().stream()
//                .filter(stageDocument -> stageDocument.getId().equals(turnsAdded.getStageId().toString()))
//                .findFirst()
//                .ifPresent(stageDocument -> stageDocument.setTurns(turnDocuments));

        competitionMongoRepository.save(competitionDocument);
    }

    private SeasonDocument convertSeason(Season season, CompetitionDocument competitionDocument) {
        return SeasonDocument.builder()
                .competitionDocumentId(competitionDocument.getId())
                .id(season.getId().getSeasonUuid().toString())
                .name(season.getName())
                .stages(Lists.newArrayList())
                .build();
    }

    private StageDocument convertStage(Stage stage) {
        List<TurnDocument> turnQueries = stage.getTurns().stream().map(this::convertTurn).collect(Collectors.toList());
        return StageDocument
                .builder()
                .name(stage.getName())
                .stageRuleSet(stage.getStageRuleSet())
                .id(stage.getId())
                .turns(turnQueries)
                .interval(stage.getInterval())
                .build();
    }
    
    private TurnDocument convertTurn(Turn turn) {
        return TurnDocument.builder()
                .matcheIds(turn.getMatches().stream().map(MatchId::getValue).collect(Collectors.toList()))
                .interval(turn.getInterval())
                .build();
    }
}