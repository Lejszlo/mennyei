package com.sp.organizer.query.updater.competition.handler;

import com.sp.organizer.api.event.competition.*;
import com.sp.organizer.api.value.competition.season.Season;
import com.sp.organizer.query.updater.club.entity.ClubDocument;
import com.sp.organizer.query.updater.club.repository.ClubQueryMongoRepository;
import com.sp.organizer.query.updater.competition.entity.CompetitionDocument;
import com.sp.organizer.query.updater.competition.entity.SeasonDocument;
import com.sp.organizer.query.updater.competition.entity.TurnDocument;
import com.sp.organizer.query.updater.competition.repository.CompetitionQueryMongoRepository;
import io.eventuate.DispatchedEvent;
import com.sp.organizer.query.updater.competition.entity.StageDocument;
import com.sp.organizer.api.value.competition.season.Stage;
import com.sp.organizer.api.value.competition.season.Turn;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@EventSubscriber
@Component
public class CompetitionEventHandler {

    private final CompetitionQueryMongoRepository competitionMongoRepository;

    private final ClubQueryMongoRepository clubMongoRepository;


    @Autowired
    public CompetitionEventHandler(CompetitionQueryMongoRepository competitionMongoRepository,
                                   ClubQueryMongoRepository clubMongoRepository) {
        this.competitionMongoRepository = competitionMongoRepository;
        this.clubMongoRepository = clubMongoRepository;
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
    public void addSeason(DispatchedEvent<SeasonAdded> dispatchedEvent) {
        SeasonAdded seasonAdded = dispatchedEvent.getEvent();
        String competitionId = dispatchedEvent.getEntityId();
        CompetitionDocument competitionDocument = competitionMongoRepository.findOne(competitionId);

        SeasonDocument seasonDocument = convertSeason(seasonAdded.getSeason(), competitionDocument);
        competitionDocument.getSeasons().add(seasonDocument);

        competitionMongoRepository.save(competitionDocument);
    }

    @EventHandlerMethod
    public void addStage(DispatchedEvent<StageAdded> dispatchedEvent) {
        StageAdded stageAddedEvent = dispatchedEvent.getEvent();
        String competitionId = dispatchedEvent.getEntityId();
        CompetitionDocument competitionDocument = competitionMongoRepository.findOne(competitionId);

        StageDocument stageDocument = convertStage(stageAddedEvent.getStage(), competitionDocument);
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
        CompetitionDocument competitionDocument = competitionMongoRepository.findOne(competitionId);

        Set<ClubDocument> clubDocuments = clubsAdded
                .getClubIds()
                .stream()
                .map(clubMongoRepository::findOne)
                .collect(Collectors.toSet());

        competitionDocument
                .getSeasons()
                .stream().filter(s ->s.getId().equals(clubsAdded.getSeasonId().toString()))
                .findFirst()
                .orElseThrow(Exception::new)// TODO másik exception
                .getStages().stream()
                .filter(stageDocument -> stageDocument.getId().equals(clubsAdded.getStageId().toString()))
                .findFirst()
                .ifPresent(stageDocument -> stageDocument.setClubs(clubDocuments));

        competitionMongoRepository.save(competitionDocument);
    }

    @EventHandlerMethod
    public void addTurnsToStage(DispatchedEvent<TurnsAdded> dispatchedEvent) {
        TurnsAdded turnsAdded = dispatchedEvent.getEvent();
        String competitionId = dispatchedEvent.getEntityId();
        CompetitionDocument competitionDocument = competitionMongoRepository.findOne(competitionId);

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

    private StageDocument convertStage(Stage stage, CompetitionDocument competitionDocument) {
        List<TurnDocument> turnQueries = stage.getTurns().stream().map(this::convertTurn).collect(Collectors.toList());
        return StageDocument
                .builder()
                .name(stage.getName())
                .stageRuleSet(stage.getStageRuleSet())
                .id(stage.getId().toString())
                .competitionDocumentId(competitionDocument.getId())
                .turns(turnQueries)
                .interval(stage.getInterval())
                .build();
    }
    
    private TurnDocument convertTurn(Turn turn) {
        return TurnDocument.builder(turn.getIndex())
                .matcheIds(turn.getMatches())
                .interval(turn.getInterval())
                .build();
    }
}