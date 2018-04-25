package com.sp.organizer.query.updater.competition.handler;

import com.sp.organizer.api.event.competition.ClubsAddedToStage;
import com.sp.organizer.query.updater.club.entity.ClubDocument;
import com.sp.organizer.query.updater.club.repository.ClubQueryMongoRepository;
import com.sp.organizer.query.updater.competition.entity.CompetitionDocument;
import com.sp.organizer.query.updater.competition.entity.TurnDocument;
import com.sp.organizer.query.updater.competition.repository.CompetitionQueryMongoRepository;
import com.sp.organizer.query.updater.competition.service.CompetitionTableService;
import com.sp.organizer.api.event.competition.CompetitionAdded;
import com.sp.organizer.api.event.competition.StageAdded;
import io.eventuate.DispatchedEvent;
import com.sp.organizer.query.updater.competition.entity.StageDocument;
import com.sp.match.api.event.MatchPlayed;
import com.sp.organizer.api.value.competition.season.Stage;
import com.sp.organizer.api.value.competition.season.Turn;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@EventSubscriber
@Component
public class CompetitionEventHandler {

    private final CompetitionQueryMongoRepository competitionMongoRepository;

    private final ClubQueryMongoRepository clubMongoRepository;

    private final CompetitionTableService competitionTableService;

    @Autowired
    public CompetitionEventHandler(CompetitionQueryMongoRepository competitionMongoRepository,
                                   ClubQueryMongoRepository clubMongoRepository, CompetitionTableService competitionTableService) {
        this.competitionMongoRepository = competitionMongoRepository;
        this.clubMongoRepository = clubMongoRepository;
        this.competitionTableService = competitionTableService;
    }

    @EventHandlerMethod
    public void createCompetition(DispatchedEvent<CompetitionAdded> dispatchedEvent) {
        CompetitionAdded competitionAddedEvent = dispatchedEvent.getEvent();
        String competitionId = dispatchedEvent.getEntityId();
        CompetitionDocument competitionQuery = CompetitionDocument.builder(competitionAddedEvent.getCompetitionInfo())
        		.id(competitionId)
                .stages(Lists.newArrayList())
        		.build();
        competitionMongoRepository.save(competitionQuery);
    }

    @EventHandlerMethod
    public void addStage(DispatchedEvent<StageAdded> dispatchedEvent) {
        StageAdded stageAddedEvent = dispatchedEvent.getEvent();
        String competitionId = dispatchedEvent.getEntityId();
        CompetitionDocument competitionDocument = competitionMongoRepository.findOne(competitionId);

        Set<ClubDocument> clubQueries = stageAddedEvent
                .getStage()
                .getClubIds()
                .stream()
                .map(clubMongoRepository::findOne)
                .collect(Collectors.toSet());

        StageDocument stageDocument = convertStage(clubQueries, stageAddedEvent.getStage(), competitionDocument);
        competitionDocument.getStages().add(stageDocument);

        competitionTableService.createTables(stageDocument);
        competitionMongoRepository.save(competitionDocument);
    }

    @EventHandlerMethod
    public void addClubsToStage(DispatchedEvent<ClubsAddedToStage> dispatchedEvent) {
        ClubsAddedToStage clubsAddedToStage = dispatchedEvent.getEvent();
        String competitionId = dispatchedEvent.getEntityId();
        CompetitionDocument competitionDocument = competitionMongoRepository.findOne(competitionId);

        Set<ClubDocument> clubDocuments = clubsAddedToStage
                .getClubIds()
                .stream()
                .map(UUID::toString)
                .map(clubMongoRepository::findOne)
                .collect(Collectors.toSet());

        competitionDocument.getStages().stream()
                .filter(stageDocument -> stageDocument.getId().equals(clubsAddedToStage.toString()))
                .findFirst()
                .ifPresent(stageDocument -> stageDocument.setClubs(clubDocuments));

        competitionTableService.updateTables(clubsAddedToStage.getStageId(), clubDocuments);
        competitionMongoRepository.save(competitionDocument);
    }

    @EventHandlerMethod
    public void matchPlayed(DispatchedEvent<MatchPlayed> dispatchedEvent) {
        MatchPlayed matchPlayed = dispatchedEvent.getEvent();
        StageDocument stageDocument = competitionTableService.getStageTable(matchPlayed.getStageId());
        competitionTableService.refreshTable(matchPlayed, stageDocument);
    }

    private StageDocument convertStage(Set<ClubDocument> clubQueries, Stage stage, CompetitionDocument competitionDocument) {
        List<TurnDocument> turnQueries = stage.getTurns().stream().map(this::convertTurn).collect(Collectors.toList());
        return StageDocument
                .builder()
                .name(stage.getName())
                .stageRuleSet(stage.getStageRuleSet())
                .id(stage.getId().toString())
                .competitionDocumentId(competitionDocument.getId())
                .turns(turnQueries)
                .clubs(clubQueries)
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